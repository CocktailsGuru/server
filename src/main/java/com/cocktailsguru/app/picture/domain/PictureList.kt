package com.cocktailsguru.app.picture.domain

import com.cocktailsguru.app.common.domain.ObjectList
import com.cocktailsguru.app.common.domain.PagingInfo

data class PictureList(
        override val objectList: List<Picture>,
        override val pagingInfo: PagingInfo
) : ObjectList<Picture>