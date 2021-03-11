package pushes.interfaces

import Gender

interface IGenderPush : IPush {
    val gender : Gender
}