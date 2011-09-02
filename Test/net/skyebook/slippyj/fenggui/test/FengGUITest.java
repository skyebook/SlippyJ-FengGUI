/*
 * FengGUI - Java GUIs in OpenGL (http://www.fenggui.org)
 * 
 * Copyright (C) 2005-2009 FengGUI Project
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details:
 * http://www.gnu.org/copyleft/lesser.html#TOC3
 * 
 * $Id: FengGUI.java 654 2009-08-10 22:57:36Z marcmenghin $
 */
package net.skyebook.slippyj.fenggui.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.JFrame;

import net.skyebook.slippyj.Coordinate;
import net.skyebook.slippyj.Palette;
import net.skyebook.slippyj.fenggui.FengGUITileContainer;
import net.skyebook.slippyj.fenggui.FengGUITileFactory;

import org.fenggui.Display;
import org.fenggui.FengGUI;
import org.fenggui.Label;
import org.fenggui.binding.render.jogl.EventBinding;
import org.fenggui.binding.render.jogl.JOGLBinding;
import org.fenggui.composite.Window;
import org.fenggui.layout.StaticLayout;
import org.fenggui.theme.DefaultTheme;

import com.sun.opengl.util.Animator;


@SuppressWarnings("serial")
public class FengGUITest extends JFrame implements GLEventListener
{

	// the GL class of JOGL
	private GL       gl;

	// the canvas on which the OpenGL will draw his stuff. We keep
	// it as a field because we need the canvas to instantiate the
	// JOGL binding.
	private GLCanvas canvas  = null;

	// The root of the Widget tree.
	private Display  display = null;
	
	private static Executor thread = Executors.newCachedThreadPool();

	/**
	 * Creates the HelloWorld example app.
	 *
	 */
	public FengGUITest()
	{
		canvas = new GLCanvas();

		canvas.addGLEventListener(this);

		getContentPane().add(canvas, java.awt.BorderLayout.CENTER);
		setSize(1680, 1050);
		setTitle("FengGUI - Hello FengGUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// we use the Animator to constantly update
		// the screen
		Animator animator = new Animator(canvas);
		animator.start();
	}

	/**
	 * Build the GUI.
	 */
	public void buildGUI()
	{
		display = FengGUI.createDisplay(new JOGLBinding(canvas));

		new EventBinding(canvas, display);

		Window w = FengGUI.createWindow(true, true);
		w.setTitle("SlippyJ FengGUI Test");
		//w.setLayoutManager(new StaticLayout());

		final FengGUITileContainer ftc = FengGUI.createWidget(FengGUITileContainer.class);
		

		w.getContentContainer().addWidget(ftc);
		System.out.println("ftc width: " + ftc.getWidth());
		w.setXY(0, 0);
		w.layout();
		display.addWidget(w);
		ftc.setSize(1000, 1000);
		System.out.println("ftc width: " + ftc.getWidth());
		Coordinate center = new Coordinate(41, -74);
		Palette palette = new Palette(center, 12, ftc, new FengGUITileFactory(Palette.OSMSlippyServer));

		thread.execute(new Runnable() {
			
			@Override
			public void run() {
				
			}
		});
		


	}

	public void display(GLAutoDrawable arg0){
		gl.glLoadIdentity();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		// pass the control over the OpenGL context to FengGUI so that
		// it can render the GUI.
		display.display();
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height){
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	/**
	 * JOGL callback method
	 */
	public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2){
		// does nothing
	}

	/**
	 * JOGL callback method
	 */
	public void init(GLAutoDrawable drawable){
		gl = drawable.getGL();
		gl.glClearColor(1.0f, 0.8f, 0.2f, 0.0f);

		//set a theme to FengGUI we use the Default theme here.
		FengGUI.setTheme(new DefaultTheme());

		// we build the GUI in the render thread! This is important
		// because textures can only be processed in the rendering
		// thread
		buildGUI();
	}

	/**
	 * Entrance point to the HelloWorld example.
	 */
	public static void main(String[] args){
		new FengGUITest();
	}

}
