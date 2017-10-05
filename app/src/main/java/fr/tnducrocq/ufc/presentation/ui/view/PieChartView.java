package fr.tnducrocq.ufc.presentation.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.view.View;

import fr.tnducrocq.ufc.presentation.R;

/**
 * Created by tony on 03/10/2017.
 */

public class PieChartView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float centerX;
    private float centerY;
    private float radius;
    private Portion[] portions;

    private final int mColor;
    @FloatRange(from = 0.1, to = 0.8)
    private final float mRadius;

    public PieChartView(Context context) {
        super(context);
        mColor = 0xFFFFFFFF;
        mRadius = 0.2f;

        mockPortions();
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PieChart, 0, 0);
        try {
            mColor = a.getColor(R.styleable.PieChart_color, 0xFFFFFFFF);
            mRadius = a.getFloat(R.styleable.PieChart_test, 0.2f);
        } finally {
            a.recycle();
        }

        mockPortions();
    }

    private void mockPortions() {
        portions = new Portion[2];
        portions[0] = new Portion("Réussi\n(473)", "48%", 0.5f, 0xFFDB1528);// getResources().getColor(R.color.colorPrimary));
        portions[1] = new Portion("Tentative\n(993)", null, 0.50f, 0xFF262626);//getResources().getColor(R.color.blue_grey_400));
        // portions[2] = new Portion("n3", "p3", 0.3f, 0xFF0000FF);//getResources().getColor(R.color.status_bar_back));
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);

        centerX = width / 2;
        centerY = height / 2;
        radius = Math.min(width * mRadius, height * mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        validPortions();

        // Circle
        paint.setShader(null);
        paint.setColor(mColor);
        canvas.drawCircle(centerX, centerY, radius, paint);

        // --- Portions ---
        final RectF oval = new RectF();
        oval.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        int startAngle = -90;
        for (int i = 0; i < portions.length; i++) {
            Portion portion = portions[i];
            paint.setColor(portion.color);
            paint.setStrokeWidth(3f);
            paint.setTextSize(radius / 8f);

            float angle = portion.value * 360;
            canvas.drawArc(oval, startAngle, angle, true, paint);

            float middleAngle = startAngle + angle / 2;
            float radianMiddleAngle = (float) ((2 * Math.PI * middleAngle / 360));

            // Draw line
            float lineStartX = (float) (centerX + Math.cos(radianMiddleAngle) * radius * 1.00);
            float lineStartY = (float) (centerY + Math.sin(radianMiddleAngle) * radius * 1.00);
            float lineStopX = (float) (centerX + Math.cos(radianMiddleAngle) * radius * 1.20);
            float lineStopY = (float) (centerY + Math.sin(radianMiddleAngle) * radius * 1.20);
            canvas.drawLine(lineStartX, lineStartY, lineStopX, lineStopY, paint);

            drawText(canvas, portion.name, middleAngle, lineStopX, lineStopY);

            if (portion.percentage != null) {

                Rect textBounds = new Rect();
                float portionCenterX = (float) (centerX + Math.cos(radianMiddleAngle) * radius * 0.50);
                float portionCenterY = (float) (centerY + Math.sin(radianMiddleAngle) * radius * 0.50);
                paint.setColor(0xFFFFFFFF);
                paint.getTextBounds(portion.percentage, 0, portion.percentage.length(), textBounds);
                canvas.drawText(portion.percentage, portionCenterX - (textBounds.width() / 2), portionCenterY + (textBounds.height() / 4), paint);
            }
            startAngle += angle;
        }
    }

    private void drawText(Canvas canvas, String text, float middleAngle, float lineStopX, float lineStopY) {
        float lineTextWidth = radius * 0.1f;
        float textSpacing = radius * 0.05f;
        String[] lines = text.split("\n");


        if (lines.length > 1) {
            Rect textBounds = new Rect();
            paint.getTextBounds(lines[0], 0, lines[0].length(), textBounds);

            Rect textBounds2 = new Rect();
            paint.getTextBounds(lines[1], 0, lines[1].length(), textBounds2);

            float width = Math.max(textBounds.width(), textBounds2.width());
            float height = textBounds.height();

            float text1X;
            float text1Y;
            float text2X = 0f;
            float text2Y = 0f;
            // Draw text
            if (middleAngle >= -90.0f && middleAngle <= 90.0f) {
                // texte de gauche à droite
                text1X = lineStopX + textSpacing;
                text1Y = lineStopY;
                text2X = lineStopX + textSpacing;
                text2Y = lineStopY + height + 5;
            } else {
                // texte de gauche à droite
                text1X = lineStopX - (textSpacing + width);
                text1Y = lineStopY;
                text2X = lineStopX - (textSpacing + textBounds2.width());
                text2Y = lineStopY + height + 5;
            }
            canvas.drawText(lines[0], text1X, text1Y, paint);
            canvas.drawText(lines[1], text2X, text2Y, paint);
        } else {
            Rect textBounds = new Rect();
            paint.getTextBounds(text, 0, text.length(), textBounds);
            float width = textBounds.width();
            float height = textBounds.height();

            float textX;
            float textY;
            // Draw text
            if (middleAngle >= -90.0f && middleAngle <= 90.0f) {
                // texte de gauche à droite
                textX = lineStopX + textSpacing;
                textY = lineStopY + (height / 4);
            } else {
                // texte de gauche à droite
                textX = lineStopX - (textSpacing + width);
                textY = lineStopY + (height / 4);
            }
            canvas.drawText(text, textX, textY, paint);
        }


        //String[] lines = portion.percentage.split("\\\n");
    }

    private void validPortions() {
        float total = 0.0f;
        for (int i = 0; i < portions.length; i++) {
            total += portions[i].value;
        }
        if (total > 1.0f) {
            throw new ArithmeticException("total of the values exceeds 1.0");
        }
    }

    public static class Portion {
        String name;

        @FloatRange(from = 0.0, to = 1.0, fromInclusive = false, toInclusive = true)
        float value;
        String percentage;
        int color;

        Portion(String name, String percentage, float value, int color) {
            this.name = name;
            this.percentage = percentage;
            this.value = value;
            this.color = color;
        }
    }
}
