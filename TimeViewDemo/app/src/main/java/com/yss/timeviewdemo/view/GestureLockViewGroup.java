package com.yss.timeviewdemo.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.yss.timeviewdemo.R;
import com.yss.timeviewdemo.view.GestureLockView.Mode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class GestureLockViewGroup extends RelativeLayout {

	public static String Tag = "GestureLockViewGroup";
	private int mNoFingerInnerColor = Color.parseColor("#4B4B4B");
	private int mNoFingerOutterColor = Color.parseColor("#111111");
	private int mFingerOnColor = Color.parseColor("#0000FF");
	private int mFingerUpColor = Color.parseColor("#EC1328");
	private int mLineViewCount = 3;
	private int mTotalTryTimes = 5;
	private int mTryTimes = 5;
	private Paint mPaint;
	private Path mPath;
	private int mWidth;
	private int mHeight;
	private GestureLockView[] mGestureLockViews;
	private int mGestureLockViewWidth;
	private int mMarginBetweenLockView;
	private List<Integer> mChoice;
	private int mLastPathX;
	private int mLastPathY;
	private Point mTemTerminal;
	private GestureLockViewGroupListener gestureLockViewGroupListener;
	private Integer[] mAnswer = { 1, 2, 5, 8 };//�����д��������,ʵ��ʹ���п��԰�������ڱ���sp��

	public GestureLockViewGroup(Context context) {
		this(context, null);
	}

	public GestureLockViewGroup(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GestureLockViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		/**
		 * ��ȡ�Զ������Ե�ֵ
		 */
		TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GestureLockViewGroup, defStyle, 0);
		int indexCount = array.getIndexCount();
		for (int i = 0; i < indexCount; i++) {
			int index = array.getIndex(i);
			switch (index) {
			case R.styleable.GestureLockViewGroup_no_finger_inner_color:
				mNoFingerInnerColor = array.getColor(index, Color.parseColor("#4B4B4B"));
				break;
			case R.styleable.GestureLockViewGroup_no_finger_outter_color:
				mNoFingerOutterColor = array.getColor(index, Color.parseColor("#111111"));
				break;
			case R.styleable.GestureLockViewGroup_finger_on_color:
				mFingerOnColor = array.getColor(index, Color.parseColor("#0000FF"));
				break;
			case R.styleable.GestureLockViewGroup_finger_up_color:
				mFingerUpColor = array.getColor(index, Color.parseColor("#EC1328"));
				break;
			case R.styleable.GestureLockViewGroup_line_view_count:
				mLineViewCount = array.getInt(index, 3);
				break;
			case R.styleable.GestureLockViewGroup_try_times:
				mTryTimes = array.getInt(index, 5);
			default:
				break;
			}
		}

		array.recycle();

		/**
		 * ��ʼ������:
		 * 1.��ʼ������
		 * 2.��ʼ���û���ָ������ԲȦ��·��
		 * 3.���������ʵ����
		 */
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeJoin(Paint.Join.ROUND);

		mPath = new Path();
		mChoice = new ArrayList<>();
		mTemTerminal = new Point();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		// mWidth = MeasureSpec.getSize(widthMeasureSpec);
		// mHeight = MeasureSpec.getSize(heightMeasureSpec);
		mHeight = getMeasuredHeight();
		mWidth = getMeasuredWidth();
		/**
		 * ����ط��������е㲻һ��,����ط����Լ�Ҳ���е�����!
		 * ��ע�͵��Ǵ����,����������ڲ�������ȷ����ֵ,���ҿ���ڸ�ʱ���е�����,Ȼ��ĳ��������Լ���
		 */

		
		mHeight = mWidth = mWidth > mHeight ? mHeight : mWidth;//ȡ��ߵ���Сֵ

		if (mGestureLockViews == null) {
			mGestureLockViews = new GestureLockView[mLineViewCount * mLineViewCount];
			mGestureLockViewWidth = (int) (4 * mWidth * 1.0f / (5 * mLineViewCount + 1));//�����ÿ��СԲȦ�Ŀ��,��new GestureLockView��ʱ����Ϊ��������
			mMarginBetweenLockView = (int) (mGestureLockViewWidth * 0.25);//�趨ԲȦ֮��ļ��
			mPaint.setStrokeWidth(mGestureLockViewWidth * 0.25f);//�趨����û���ָ·���Ļ��ʵĿ��

			for (int i = 0; i < mGestureLockViews.length; i++) {
				mGestureLockViews[i] = new GestureLockView(getContext(), mNoFingerInnerColor, mNoFingerOutterColor, mFingerOnColor, mFingerUpColor, mGestureLockViewWidth);
				mGestureLockViews[i].setId(i + 1);//�����ÿ��GestureLockView���id ���ڼ�¼�û���ָ������view �Ա��Ƿ�����ȷ����Ҳ�ǿ����
				LayoutParams lockParams = new LayoutParams(mGestureLockViewWidth, mGestureLockViewWidth);

				if (i % mLineViewCount != 0) {
					lockParams.addRule(RelativeLayout.RIGHT_OF, mGestureLockViews[i - 1].getId());
				}

				if (i > mLineViewCount - 1) {
					lockParams.addRule(RelativeLayout.BELOW, mGestureLockViews[i - mLineViewCount].getId());
				}

				int rightMargin = mMarginBetweenLockView;
				int bottomMargin = mMarginBetweenLockView;
				int leftMargin = 0;
				int topMargin = 0;
				if (i >= 0 && i < mLineViewCount) {
					topMargin = mMarginBetweenLockView;
				}

				if (i % mLineViewCount == 0) {
					leftMargin = mMarginBetweenLockView;
				}

				lockParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
				addView(mGestureLockViews[i], lockParams);
				Log.i(Tag, i + ":left +" + leftMargin + ":topMargin +" + topMargin + ":rightMargin +" + rightMargin + ":rightMargin +" + leftMargin);
			}
		}

	}

	/**
	 * ��ָ�Ĵ����߼�:
	 * 1.down��ʱ��������ݻָ���ʼ��
	 * 2.move��ʱ��:
	 * 			����ָ�ƶ���ĳһ���ϵ�ʱ��,�жϸĵ��Ƿ��Ѿ���ѡ�����,û�б�ѡ���ͼ��뵽mChoice��,������״̬Ϊfinget_on
	 * 			��һ�����id�������,�������һ���������Ϊ���ѡ����Ǹ�����������겢��ȷ��һ����ʱ��ʱ���յ������ָ��λ��
	 * 3.up��ʱ��:
	 * 			���������Ѿ���ѡ�е�״̬δfinger_up
	 * 			�ж��Ƿ�����Ѿ�����,�����ֱ�ӽ���
	 * 			Ȼ��ȥУ����ָ·���ϵ�id�Ƿ������һ��
	 * 			ȥ������������ԲȦ��Բ������ȥȷ��������ǰ�ߵ�С���ǵĽǶȲ����ø�СԲȦ
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();

		switch (action) {

		case MotionEvent.ACTION_DOWN:
			reset();
			break;
		case MotionEvent.ACTION_MOVE:
			mPaint.setColor(mFingerOnColor);
			mPaint.setAlpha(50);
			GestureLockView gestureLockView = getChildByPos(x, y);
			if (gestureLockView != null) {
				int id = gestureLockView.getId();
				if (!mChoice.contains(id)) {
					if (gestureLockViewGroupListener != null) {
						gestureLockViewGroupListener.onBlockSelected(id);
					}
					mChoice.add(gestureLockView.getId());
					gestureLockView.setMode(Mode.STATUS_FINGER_ON);
					mLastPathX = gestureLockView.getLeft() / 2 + gestureLockView.getRight() / 2;
					mLastPathY = gestureLockView.getTop() / 2 + gestureLockView.getBottom() / 2;

					if (mChoice.size() == 1) {
						mPath.moveTo(mLastPathX, mLastPathY);
					} else {
						mPath.lineTo(mLastPathX, mLastPathY);
					}

				}
			}

			mTemTerminal.x = x;
			mTemTerminal.y = y;
			break;
		case MotionEvent.ACTION_UP:

			mPaint.setColor(mFingerUpColor);
			mPaint.setAlpha(50);
			mTemTerminal.x = mLastPathX;
			mTemTerminal.y = mLastPathY;

			changeItemModeToUp();

			for (int i = 0; i < mChoice.size() - 1; i++) {
				Integer firstChildId = mChoice.get(i);
				Integer nextChildId = mChoice.get(i + 1);
				GestureLockView firstChild = (GestureLockView) findViewById(firstChildId);
				GestureLockView nextChild = (GestureLockView) findViewById(nextChildId);

				int dx = nextChild.getLeft() - firstChild.getLeft();
				int dy = nextChild.getTop() - firstChild.getTop();
				int angle = (int) Math.toDegrees(Math.atan2(dy, dx)) + 90;
				firstChild.setArrowDegree(angle);
			}

			this.mTryTimes--;
			if (mTryTimes < 0) {
				if (gestureLockViewGroupListener != null) {
					gestureLockViewGroupListener.isOutOfTryTime(true);
				}
				break;
			} else {
				if (gestureLockViewGroupListener != null) {
					gestureLockViewGroupListener.isOutOfTryTime(false);
				}
			}

			Integer[] array = mChoice.toArray(new Integer[] {});
			if (gestureLockViewGroupListener != null) {
				gestureLockViewGroupListener.isGestureMatched(checkAnswer(array));
			}
			if (checkAnswer(array)) {
				mTryTimes = mTotalTryTimes;
			}
			break;

		default:
			break;
		}
		invalidate();
		return true;
	}

	/**
	 * �����ж����������Ƿ���ȷ
	 * @param array
	 * @return
	 */
	private boolean checkAnswer(Integer[] array) {
		if (array.length != mAnswer.length) {
			return false;
		}

		for (int i = 0; i < array.length; i++) {
			if (array[i] != mAnswer[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		//��ʱ�������Ѿ�ѡ��ĵ�֮�������
		if (mPath != null) {
			canvas.drawPath(mPath, mPaint);
		}

		//��ʱ���������һ��ѡ������ָδ�ͷ�ʱ֮�����,��ʵʱ�ı��
		if (mChoice.size() > 0) {
			canvas.drawLine(mLastPathX, mLastPathY, mTemTerminal.x, mTemTerminal.y, mPaint);
		}
	}

	private void changeItemModeToUp() {
		for (GestureLockView gestureLockView : mGestureLockViews) {
			if (mChoice.contains(gestureLockView.getId())) {
				gestureLockView.setMode(Mode.STATUS_FINGER_UP);
			}
		}
	}

	private void reset() {
		mChoice.clear();
		mPath.reset();
		for (int i = 0; i < mGestureLockViews.length; i++) {
			mGestureLockViews[i].setMode(Mode.STATUS_NO_FINGER);
			mGestureLockViews[i].setArrowDegree(-1);
		}
	}

	private GestureLockView getChildByPos(int x, int y) {
		for (GestureLockView gestureLockView : mGestureLockViews) {
			if (checkPositionInChild(gestureLockView, x, y)) {
				return gestureLockView;
			}
		}
		return null;
	}

	private boolean checkPositionInChild(View child, int x, int y) {
		int padding = (int) (mGestureLockViewWidth * 0.15);
		if (x >= child.getLeft() + padding && x <= child.getRight() - padding && y >= child.getTop() + padding && y <= child.getBottom() - padding) {
			return true;
		}
		return false;
	}

	public void setGestureViewGroupListener(GestureLockViewGroupListener listener) {
		this.gestureLockViewGroupListener = listener;
	}

	public interface GestureLockViewGroupListener {
		public void onBlockSelected(int cid);

		public void isGestureMatched(boolean matched);

		public void isOutOfTryTime(boolean outOfTryTime);
	}

	public void setAnswer(Integer[] setedAnswer) {
		this.mAnswer = setedAnswer;
	}
}
