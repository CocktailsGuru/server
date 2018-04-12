package com.cocktailsguru.app.user.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.cocktail.service.CocktailService
import com.cocktailsguru.app.user.domain.User
import com.cocktailsguru.app.user.domain.rating.RateObjectRequest
import com.cocktailsguru.app.user.domain.rating.RatingResultType
import com.cocktailsguru.app.user.domain.rating.RatingType
import com.cocktailsguru.app.user.domain.rating.UserRating
import com.cocktailsguru.app.user.repository.UserRatingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
open class UserRatingServiceImpl @Autowired constructor(
        private val cocktailService: CocktailService,
        private val userService: UserService,
        private val ratingRepository: UserRatingRepository
) : UserRatingService {

    override fun getRatingsOfUser(user: User): List<UserRating> = ratingRepository.findByUser(user)

    @Transactional
    override fun rateCocktail(ratingRequest: RateObjectRequest): RatingResultType {
        val cocktail = cocktailService.findCocktail(ratingRequest.objectId) ?: return RatingResultType.OBJECT_NOT_FOUND

        val user = userService.verifyUser(ratingRequest.userToken) ?: return RatingResultType.USER_NOT_FOUND

        val alreadyRated = ratingRepository.existsByUserAndObjectTypeAndObjectForeignKey(user, CocktailObjectType.COCKTAIL, cocktail.id)

        if (alreadyRated) {
            return RatingResultType.ALREADY_RATED
        }

        val userRating = UserRating(
                0,
                CocktailObjectType.COCKTAIL,
                cocktail.id,
                user,
                ratingRequest.rating,
                LocalDateTime.now()
        )

        ratingRepository.save(userRating)
        user.numCocktailsRated++

        when (ratingRequest.rating) {
            RatingType.ONE -> cocktail.numRating1++
            RatingType.TWO -> cocktail.numRating2++
            RatingType.THREE -> cocktail.numRating3++
            RatingType.FOUR -> cocktail.numRating4++
            RatingType.FIVE -> cocktail.numRating5++
        }

        return RatingResultType.OK
    }

}