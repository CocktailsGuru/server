package com.cocktailsguru.app.picture.service

import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.picture.repository.PictureRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PictureServiceImpl @Autowired constructor(
        private val pictureRepository: PictureRepository
) : PictureService {

    override fun getPictureListForObject(objectType: CocktailObjectType, id: Long) =
            pictureRepository.findByObjectTypeAndObjectForeignKey(objectType, id)

}