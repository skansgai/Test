package com.yss.timeviewdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.view.View;

public class GestureLockView extends View {

	/**
	 * STATUS_NO_FINGER Ĭ�ϵ�״̬ STATUS_FINGER_ON ����ѡ��ʱ STATUS_FINGER_UP ��ѡ�к����
	 * 
	 * @author 
	 */
	enum Mode {
		STATUS_NO_FINGER, STATUS_FINGER_ON, STATUS_FINGER_UP
	}

	private int colorNoFingetInner;
	private int colorNofingerOutter;
	private int colorFingerOn;
	private int colorFingerUp;

	private Mode mCurrentMode = Mode.STATUS_NO_FINGER;
	private Paint mPaint;
	private Path mArrowPath;
	private int mWidth;// view�Ŀ�
	private int mHeight;// view�ĸ�
	private int mRadius;// �뾶
	private int mCenterX;// Բ��x����
	private int mCenterY;// Բ��Y����
	private int mStrokeWidth = 2;
	private float mArrowLength;// �����εĸ߶�
	private int mArrowDegree = -1;

	public GestureLockView(Context context, int colorNoFingetInner, int colorNofingerOutter, int colorFingerOn, int colorFingerUp, int mWidth) {
		super(context);
		/**
		 * ���г�ʼ���Ĳ���:
		 * 1.ȷ��������ɫ
		 * 2.��ʼ������mPaint
		 * 3.��ʼ��Ҫ�������ǵ�·��
		 * ---�˴��ͺ�������е㲻һ��,���ڴ˴���ԲȦ�Ŀ�����ڸ�ViewGroup����󴫽�����
		 */
		
		this.colorNoFingetInner = colorNoFingetInner;
		this.colorNofingerOutter = colorNofingerOutter;
		this.colorFingerOn = colorFingerOn;
		this.colorFingerUp = colorFingerUp;
		this.mWidth = this.mHeight = mWidth;

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mArrowPath = new Path();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);
		mRadius = mCenterX = mCenterY = mWidth / 2;
		mArrowLength = (float) (mRadius * 0.3);
		mRadius -= mStrokeWidth / 2;

		mArrowPath.moveTo(mCenterX, mStrokeWidth + 2);
		mArrowPath.lineTo(mCenterX - mArrowLength * 0.8F, mStrokeWidth + 2 + mArrowLength);
		mArrowPath.lineTo(mCenterX + mArrowLength * 0.8F, mStrokeWidth + 2 + mArrowLength);
		mArrowPath.close();
		mArrowPath.setFillType(FillType.WINDING);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		switch (mCurrentMode) {
		case STATUS_NO_FINGER:
			// Ĭ��״̬��
			mPaint.setStyle(Style.FILL);
			mPaint.setColor(colorNofingerOutter);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);

			mPaint.setColor(colorNoFingetInner);
			canvas.drawCircle(mCenterX, mCenterY, mRadius * 0.2f, mPaint);
			break;
		case STATUS_FINGER_ON:
			// ��ѡ��״̬
			mPaint.setStyle(Style.STROKE);
			mPaint.setColor(colorFingerOn);
			mPaint.setStrokeWidth(mStrokeWidth);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);

			mPaint.setStyle(Style.FILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius * 0.2f, mPaint);
			break;

		case STATUS_FINGER_UP:
			// ��ѡ�к�ſ�
			mPaint.setColor(colorFingerUp);
			mPaint.setStyle(Style.STROKE);
			mPaint.setStrokeWidth(mStrokeWidth);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);

			mPaint.setStyle(Style.FILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius * 0.2f, mPaint);

			// ��С������
			drawArray(canvas);

			break;
		default:
			break;
		}
		mPaint.setColor(Color.WHITE);
		mPaint.setStrokeWidth(20);
		String id = String.valueOf(getId());
		canvas.drawText(id, 0, id.length(), mCenterX, mCenterY, mPaint);
	}

	private void drawArray(Canvas canvas) {
		if (mArrowDegree != -1) {
			mPaint.setStyle(Style.FILL);
			canvas.save();
			canvas.rotate(mArrowDegree, mCenterX, mCenterY); // ��ת�Ĳ��ǻ���,���ǻ���������ϵ
			canvas.drawPath(mArrowPath, mPaint);
			canvas.restore();
		}
	}

	/**
	 * ���õ�ǰģʽ���ػ����
	 * 
	 * @param mode
	 */
	public void setMode(Mode mode) {
		this.mCurrentMode = mode;
		invalidate();
	}

	/**
	 * ����С���ǵĽǶ�
	 * @param degree
	 */
	public void setArrowDegree(int degree) {
		this.mArrowDegree = degree;
	}

	public int getArrowDegree() {
		return this.mArrowDegree;
	}
}
