package com.cocktailsguru.app.picture.service

import com.cocktailsguru.app.cocktail.domain.Cocktail
import com.cocktailsguru.app.cocktail.domain.CocktailObjectType
import com.cocktailsguru.app.common.domain.PagingInfo
import com.cocktailsguru.app.picture.domain.PictureList
import com.cocktailsguru.app.picture.repository.PictureRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PictureServiceImpl @Autowired constructor(
        private val pictureRepository: PictureRepository
) : PictureService {

    override fun getPictureList(cocktail: Cocktail, pagingInfo: PagingInfo) =
            getPictureListForObject(CocktailObjectType.COCKTAIL, cocktail.id, pagingInfo)

//    override fun findFirstPictureForObject(objectType: CocktailObjectType, id: Long): Picture? {
//        return pictureRepository.findFirstByObjectTypeAndObjectForeignKey(objectType, id)
//    }

//    override fun getPictureList(cocktail: Cocktail) = getPictureListForObject(CocktailObjectType.COCKTAIL, cocktail.id)

    override fun getPictureListForObject(objectType: CocktailObjectType, id: Long, pagingInfo: PagingInfo): PictureList {
        return if (pagingInfo.pageSize == 0) {
            PictureList(listOf(), pagingInfo)
        } else {
            val pictureList = pictureRepository.findByObjectTypeAndObjectForeignKey(objectType, id, pagingInfo.toPageRequest())
            PictureList(pictureList, pagingInfo)
        }
    }
}