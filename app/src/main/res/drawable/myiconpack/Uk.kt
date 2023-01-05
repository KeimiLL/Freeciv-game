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
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIconPack.Uk: ImageVector
    get() {
        if (_uk != null) {
            return _uk!!
        }
        _uk = Builder(name = "Uk", defaultWidth = 1200.0.dp, defaultHeight = 600.0.dp, viewportWidth
                = 60.0f, viewportHeight = 30.0f).apply {
            group {
                path(fill = SolidColor(Color(0xFF012169)), stroke = null, strokeLineWidth = 0.0f,
                        strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                        pathFillType = NonZero) {
                    moveTo(0.0f, 0.0f)
                    verticalLineToRelative(30.0f)
                    horizontalLineToRelative(60.0f)
                    verticalLineToRelative(-30.0f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 6.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(0.0f, 0.0f)
                    lineTo(60.0f, 30.0f)
                    moveTo(60.0f, 0.0f)
                    lineTo(0.0f, 30.0f)
                }
            }
            group {
                path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFFC8102E)),
                        strokeLineWidth = 4.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(0.0f, 0.0f)
                    lineTo(60.0f, 30.0f)
                    moveTo(60.0f, 0.0f)
                    lineTo(0.0f, 30.0f)
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFF000000)),
                        strokeLineWidth = 10.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(30.0f, 0.0f)
                    verticalLineToRelative(30.0f)
                    moveTo(0.0f, 15.0f)
                    horizontalLineToRelative(60.0f)
                }
                path(fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0xFFC8102E)),
                        strokeLineWidth = 6.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f, pathFillType = NonZero) {
                    moveTo(30.0f, 0.0f)
                    verticalLineToRelative(30.0f)
                    moveTo(0.0f, 15.0f)
                    horizontalLineToRelative(60.0f)
                }
            }
        }
        .build()
        return _uk!!
    }

private var _uk: ImageVector? = null
