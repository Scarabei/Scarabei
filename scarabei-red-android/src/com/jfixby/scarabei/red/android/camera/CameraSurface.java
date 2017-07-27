
package com.jfixby.scarabei.red.android.camera;

import java.io.IOException;
import java.lang.reflect.Method;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.List;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.red.android.display.AndroidDisplay;
import com.jfixby.scarabei.red.android.display.AndroidDisplay.ROTATION;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {
	private Camera camera;
	private final Context context;

	public CameraSurface (final Context context) {
		super(context);
		this.context = context;
		// We're implementing the Callback interface and want to get notified
		// about certain surface events.
		this.getHolder().addCallback(this);
		// We're changing the surface to a PUSH surface, meaning we're receiving
		// all buffer data from another component - the camera, in this case.
		this.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated (final SurfaceHolder holder) {
		// Once the surface is created, simply open a handle to the camera hardware.
// camera = Camera.open();
		this.camera = this.openFrontFacingCameraGingerbread();
	}

	@Override
	public void surfaceChanged (final SurfaceHolder holder, final int format, final int width, final int height) {
		// This method is called when the surface changes, e.g. when it's size is set.
		// We use the opportunity to initialize the camera preview display dimensions.
		final Camera.Parameters parameters = this.camera.getParameters();
		final List<CameraSize> previewSizes = this.getSupportedPreviewSizes();
		previewSizes.print("previewSizes");
		final AndroidDisplay display = this.getDefaultDisplay();
		L.d(display);
		L.d("Surface size = " + width + " x " + height);
		// You need to choose the most appropriate previewSize for your app
// Camera.Size previewSize = previewSizes.getElementAt(0);
		{

			if (display.getRotation() == AndroidDisplay.ROTATION.ROTATION_0) {
				parameters.setPreviewSize(height, width);
				this.camera.setDisplayOrientation(90);
			}

			if (display.getRotation() == AndroidDisplay.ROTATION.ROTATION_90) {
				parameters.setPreviewSize(width, height);
			}

			if (display.getRotation() == AndroidDisplay.ROTATION.ROTATION_180) {
				parameters.setPreviewSize(height, width);
			}

			if (display.getRotation() == AndroidDisplay.ROTATION.ROTATION_270) {
				parameters.setPreviewSize(width, height);
				this.camera.setDisplayOrientation(180);
			}

		}
		final float aspectRatio = (width / height);
// float closestMatch = Math.abs((float)previewSize.width / previewSize.height - aspectRatio);

// for (final Camera.Size size : previewSizes)
		{
// final float match = Math.abs((float)size.width / size.height - aspectRatio);
// if (match < closestMatch) {
// previewSize = size;
// closestMatch = match;
// }
		}
		{
			if (Integer.parseInt(Build.VERSION.SDK) >= 8) {
				this.setDisplayOrientation(this.camera, 90);
			} else {
				if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
					parameters.set("orientation", "portrait");
					parameters.set("rotation", 90);
				}
				if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
					parameters.set("orientation", "landscape");
					parameters.set("rotation", 90);
				}
			}

		}

// parameters.setPreviewSize(width, height);
// parameters.setPreviewSize(previewSize.height, previewSize.width);
		this.camera.setParameters(parameters);

		// We also assign the preview display to this surface...
		try {
			this.camera.setPreviewDisplay(holder);
			this.camera.startPreview();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private List<CameraSize> getSupportedPreviewSizes () {

		final Camera.Parameters parameters = this.camera.getParameters();
		final java.util.List<Size> javaList = parameters.getSupportedPreviewSizes();
		final List<CameraSize> previewSizes = Collections.newList();
		for (int i = 0; i < javaList.size(); i++) {
			final Size size = javaList.get(i);
			previewSizes.add(new CameraSize(size));
		}

		return previewSizes;
	}

	private AndroidDisplay getDefaultDisplay () {
		final WindowManager winMan = ((WindowManager)this.context.getSystemService(Context.WINDOW_SERVICE));
		final Display shitDisplay = winMan.getDefaultDisplay();
		final AndroidDisplay display = new AndroidDisplay(shitDisplay);
		return display;
	}

	@Override
	public void surfaceDestroyed (final SurfaceHolder holder) {
		// Once the surface gets destroyed, we stop the preview mode and release
		// the whole camera since we no longer need it.
		this.camera.stopPreview();
		this.camera.release();
		this.camera = null;
	}

	public Camera getCamera () {
		return this.camera;
	}

	private Camera openFrontFacingCameraGingerbread () {
		final int cameraCount = 0;
		Camera cam = null;
// final Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
// cameraCount = Camera.getNumberOfCameras();
// for (int camIdx = 0; camIdx < cameraCount; camIdx++)
		{
// Camera.getCameraInfo(camIdx, cameraInfo);
// if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK)
			{
				try {
					cam = Camera.open();
				} catch (final RuntimeException e) {
					e.printStackTrace();
					Log.e("camera", "Camera failed to open: " + e.getLocalizedMessage());
				}
			}
		}

		return cam;
	}

	protected void setDisplayOrientation (final Camera camera, final int angle) {
		Method downPolymorphic;
		try {
			downPolymorphic = camera.getClass().getMethod("setDisplayOrientation", new Class[] {int.class});
			if (downPolymorphic != null) {
				downPolymorphic.invoke(camera, new Object[] {angle});
			}
		} catch (final Exception e1) {
		}
	}

}
