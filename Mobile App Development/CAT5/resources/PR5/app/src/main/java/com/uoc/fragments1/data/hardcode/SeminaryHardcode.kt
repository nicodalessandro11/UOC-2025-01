package com.uoc.fragments1.data.hardcode

import com.uoc.fragments1.R
import com.uoc.fragments1.data.model.Seminary


fun SeminaryHardcode1(): List<Seminary> {
    return listOf(
        Seminary(
            id = 1,
            name = "Introduction to mobile devices",
            duration = 60,
            level = "beginner",
            image_path = "",
            image = R.drawable.introduction_mobile,
            view = null
        ),
        Seminary(
            id = 2,
            name = "Mobile programming environments",
            duration = 30,
            level = "intermediate",
            image_path = "",
            image = R.drawable.mobile_environment,
            view = null
          )

    )
}

fun SeminaryHardcode2(): List<Seminary> {
    return listOf(
        Seminary(
            id = 2,
            name = "Mobile programming environments",
            duration = 40,
            level = "intermediate",
            image_path = "",
            image = R.drawable.mobile_environment,
            view = null
        ),
        Seminary(
            id = 1,
            name = "Introduction to mobile devices",
            duration = 30,
            level = "beginner",
            image_path = "",
            image = R.drawable.introduction_mobile,
            view = null
        )

    )
}
