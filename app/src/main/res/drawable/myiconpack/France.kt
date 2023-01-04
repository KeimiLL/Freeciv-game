package myiconpack

import MyIconPack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIconPack.France: ImageVector
    get() {
        if (_france != null) {
            return _france!!
        }
        _france = Builder(name = "France", defaultWidth = 900.0.dp, defaultHeight = 600.0.dp,
                viewportWidth = 900.0f, viewportHeight = 600.0f).apply {
            path(fill = SolidColor(Color(0xFFED2939)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(900.0f)
                verticalLineToRelative(600.0f)
                horizontalLineToRelative(-900.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(600.0f)
                verticalLineToRelative(600.0f)
                horizontalLineToRelative(-600.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF002395)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(300.0f)
                verticalLineToRelative(600.0f)
                horizontalLineToRelative(-300.0f)
                close()
            }
        }
        .build()
        return _france!!
    }

private var _france: ImageVector? = null
