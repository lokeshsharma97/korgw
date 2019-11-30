package com.soywiz.korgw.win32

import com.soywiz.kgl.KmlGl
import com.soywiz.kgl.nioBuffer
import com.soywiz.kgl.nioFloatBuffer
import com.soywiz.kgl.nioIntBuffer
import com.soywiz.kmem.FBuffer
import com.soywiz.korim.awt.AwtNativeImage
import com.soywiz.korim.bitmap.NativeImage
import com.sun.jna.Function
import com.sun.jna.Library
import com.sun.jna.Pointer
import com.sun.jna.platform.win32.OpenGL32
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer

private typealias IntSize = Long
private typealias VoidPtr = ByteBuffer
private typealias IntPtr = IntBuffer
private typealias FloatPtr = FloatBuffer

interface Win32GL : Library {
    fun glActiveTexture(texture: Int)
    fun glAttachShader(program: Int, shader: Int)
    fun glBindAttribLocation(program: Int, index: Int, name: String)
    fun glBindBuffer(target: Int, buffer: Int)
    fun glBindFramebuffer(target: Int, framebuffer: Int)
    fun glBindRenderbuffer(target: Int, renderbuffer: Int)
    fun glBindTexture(target: Int, texture: Int)
    fun glBlendColor(red: Float, green: Float, blue: Float, alpha: Float)
    fun glBlendEquation(mode: Int)
    fun glBlendEquationSeparate(modeRGB: Int, modeAlpha: Int)
    fun glBlendFunc(sfactor: Int, dfactor: Int)
    fun glBlendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int)
    fun glBufferData(target: Int, size: IntSize, data: VoidPtr, usage: Int)
    fun glBufferSubData(target: Int, offset: IntSize, size: IntSize, data: VoidPtr)
    fun glCheckFramebufferStatus(target: Int): Int
    fun glClear(mask: Int)
    fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float)
    fun glClearDepth(d: Double)
    fun glClearStencil(s: Int)
    fun glColorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean)
    fun glCompileShader(shader: Int)
    fun glCompressedTexImage2D(
        target: Int,
        level: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        border: Int,
        imageSize: Int,
        data: VoidPtr
    )

    fun glCompressedTexSubImage2D(
        target: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        imageSize: Int,
        data: VoidPtr
    )

    fun glCopyTexImage2D(
        target: Int,
        level: Int,
        internalformat: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        border: Int
    )

    fun glCopyTexSubImage2D(
        target: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    )

    fun glCreateProgram(): Int
    fun glCreateShader(type: Int): Int
    fun glCullFace(mode: Int)
    fun glDeleteBuffers(n: Int, items: IntPtr)
    fun glDeleteFramebuffers(n: Int, items: IntPtr)
    fun glDeleteProgram(program: Int)
    fun glDeleteRenderbuffers(n: Int, items: IntPtr)
    fun glDeleteShader(shader: Int)
    fun glDeleteTextures(n: Int, items: IntPtr)
    fun glDepthFunc(func: Int)
    fun glDepthMask(flag: Boolean)
    fun glDepthRange(n: Double, f: Double)
    fun glDetachShader(program: Int, shader: Int)
    fun glDisable(cap: Int)
    fun glDisableVertexAttribArray(index: Int)
    fun glDrawArrays(mode: Int, first: Int, count: Int)
    fun glDrawElements(mode: Int, count: Int, type: Int, indices: IntSize)
    fun glEnable(cap: Int)
    fun glEnableVertexAttribArray(index: Int)
    fun glFinish()
    fun glFlush()
    fun glFramebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Int)
    fun glFramebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int, level: Int)
    fun glFrontFace(mode: Int)
    fun glGenBuffers(n: Int, buffers: IntPtr)
    fun glGenerateMipmap(target: Int)
    fun glGenFramebuffers(n: Int, framebuffers: IntPtr)
    fun glGenRenderbuffers(n: Int, renderbuffers: IntPtr)
    fun glGenTextures(n: Int, textures: IntPtr)
    fun glGetActiveAttrib(
        program: Int,
        index: Int,
        bufSize: Int,
        length: IntPtr,
        size: IntPtr,
        type: IntPtr,
        name: VoidPtr
    )

    fun glGetActiveUniform(
        program: Int,
        index: Int,
        bufSize: Int,
        length: IntPtr,
        size: IntPtr,
        type: IntPtr,
        name: VoidPtr
    )

    fun glGetAttachedShaders(program: Int, maxCount: Int, count: IntPtr, shaders: IntPtr)
    fun glGetAttribLocation(program: Int, name: String): Int
    fun glGetUniformLocation(program: Int, name: String): Int
    fun glGetBooleanv(pname: Int, data: VoidPtr)
    fun glGetBufferParameteriv(target: Int, pname: Int, params: IntPtr)
    fun glGetError(): Int
    fun glGetFloatv(pname: Int, data: FloatPtr)
    fun glGetFramebufferAttachmentParameteriv(target: Int, attachment: Int, pname: Int, params: IntPtr)
    fun glGetIntegerv(pname: Int, data: IntPtr)
    fun glGetProgramInfoLog(program: Int, bufSize: Int, length: IntPtr, infoLog: VoidPtr)
    fun glGetRenderbufferParameteriv(target: Int, pname: Int, params: IntPtr)
    fun glGetProgramiv(program: Int, pname: Int, params: IntPtr)
    fun glGetShaderiv(shader: Int, pname: Int, params: IntPtr)
    fun glGetShaderInfoLog(shader: Int, bufSize: Int, length: IntPtr, infoLog: VoidPtr)
    fun glGetShaderPrecisionFormat(shadertype: Int, precisiontype: Int, range: IntPtr, precision: IntPtr)
    fun glGetShaderSource(shader: Int, bufSize: Int, length: IntPtr, source: VoidPtr)
    fun glGetString(name: Int): String
    fun glGetTexParameterfv(target: Int, pname: Int, params: FloatPtr)
    fun glGetTexParameteriv(target: Int, pname: Int, params: IntPtr)
    fun glGetUniformfv(program: Int, location: Int, params: FloatPtr)
    fun glGetUniformiv(program: Int, location: Int, params: IntPtr)
    fun glGetVertexAttribfv(index: Int, pname: Int, params: FloatPtr)
    fun glGetVertexAttribiv(index: Int, pname: Int, params: IntPtr)
    fun glGetVertexAttribPointerv(index: Int, pname: Int, pointer: FBuffer)
    fun glHint(target: Int, mode: Int)
    fun glIsBuffer(buffer: Int): Boolean
    fun glIsEnabled(cap: Int): Boolean
    fun glIsFramebuffer(framebuffer: Int): Boolean
    fun glIsProgram(program: Int): Boolean
    fun glIsRenderbuffer(renderbuffer: Int): Boolean
    fun glIsShader(shader: Int): Boolean
    fun glIsTexture(texture: Int): Boolean
    fun glLineWidth(width: Float)
    fun glLinkProgram(program: Int)
    fun glPixelStorei(pname: Int, param: Int)
    fun glPolygonOffset(factor: Float, units: Float)
    fun glReadPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: VoidPtr)
    fun glReleaseShaderCompiler()
    fun glRenderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int)
    fun glSampleCoverage(value: Float, invert: Boolean)
    fun glScissor(x: Int, y: Int, width: Int, height: Int)
    fun glShaderBinary(count: Int, shaders: IntPtr, binaryformat: Int, binary: VoidPtr, length: Int)
    fun glShaderSource(shader: Int, count: IntSize, string: Array<String>, length: IntArray?)
    fun glStencilFunc(func: Int, ref: Int, mask: Int)
    fun glStencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int)
    fun glStencilMask(mask: Int)
    fun glStencilMaskSeparate(face: Int, mask: Int)
    fun glStencilOp(fail: Int, zfail: Int, zpass: Int)
    fun glStencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int)
    fun glTexImage2D(
        target: Int,
        level: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        border: Int,
        format: Int,
        type: Int,
        pixels: VoidPtr?
    )

    fun glTexImage2D(target: Int, level: Int, internalformat: Int, format: Int, type: Int, data: NativeImage)
    fun glTexParameterf(target: Int, pname: Int, param: Float)
    fun glTexParameterfv(target: Int, pname: Int, params: FloatPtr)
    fun glTexParameteri(target: Int, pname: Int, param: Int)
    fun glTexParameteriv(target: Int, pname: Int, params: IntPtr)
    fun glTexSubImage2D(
        target: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        pixels: VoidPtr
    )

    fun glUniform1f(location: Int, v0: Float)
    fun glUniform1fv(location: Int, count: Int, value: FloatPtr)
    fun glUniform1i(location: Int, v0: Int)
    fun glUniform1iv(location: Int, count: Int, value: IntPtr)
    fun glUniform2f(location: Int, v0: Float, v1: Float)
    fun glUniform2fv(location: Int, count: Int, value: FloatPtr)
    fun glUniform2i(location: Int, v0: Int, v1: Int)
    fun glUniform2iv(location: Int, count: Int, value: IntPtr)
    fun glUniform3f(location: Int, v0: Float, v1: Float, v2: Float)
    fun glUniform3fv(location: Int, count: Int, value: FloatPtr)
    fun glUniform3i(location: Int, v0: Int, v1: Int, v2: Int)
    fun glUniform3iv(location: Int, count: Int, value: IntPtr)
    fun glUniform4f(location: Int, v0: Float, v1: Float, v2: Float, v3: Float)
    fun glUniform4fv(location: Int, count: Int, value: FloatPtr)
    fun glUniform4i(location: Int, v0: Int, v1: Int, v2: Int, v3: Int)
    fun glUniform4iv(location: Int, count: Int, value: IntPtr)
    fun glUniformMatrix2fv(location: Int, count: Int, transpose: Boolean, value: FloatPtr)
    fun glUniformMatrix3fv(location: Int, count: Int, transpose: Boolean, value: FloatPtr)
    fun glUniformMatrix4fv(location: Int, count: Int, transpose: Boolean, value: FloatPtr)
    fun glUseProgram(program: Int)
    fun glValidateProgram(program: Int)
    fun glVertexAttrib1f(index: Int, x: Float)
    fun glVertexAttrib1fv(index: Int, v: FloatPtr)
    fun glVertexAttrib2f(index: Int, x: Float, y: Float)
    fun glVertexAttrib2fv(index: Int, v: FloatPtr)
    fun glVertexAttrib3f(index: Int, x: Float, y: Float, z: Float)
    fun glVertexAttrib3fv(index: Int, v: FloatPtr)
    fun glVertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float)
    fun glVertexAttrib4fv(index: Int, v: FloatPtr)
    fun glVertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: IntSize)
    fun glViewport(x: Int, y: Int, width: Int, height: Int)

    companion object : Win32GL by OpenglLoadProxy()
}

fun PointerOrNull(value: Long): Pointer? {
    if (value == 0L) return null
    return Pointer.createConstant(value)
}

fun OpenglLoadProxy(): Win32GL {
    val funcs = LinkedHashMap<Method, Function>()
    val classLoader = Win32KmlGl::class.java.classLoader
    val opengl32Lib = com.sun.jna.NativeLibrary.getInstance("opengl32")
    return Proxy.newProxyInstance(
        classLoader,
        arrayOf(Win32GL::class.java)
    ) { obj: Any?, method: Method, args: Array<Any?>? ->
        val func = funcs.getOrPut(method) {
            OpenGL32.INSTANCE.wglGetProcAddress(method.name)?.let { Function.getFunction(it) }
                ?: opengl32Lib.getFunction(method.name)
                ?: error("Can't find opengl method ${method.name}")

        }
        func.invoke(method.returnType, args)
    } as Win32GL
}


object Win32KmlGl : KmlGl() {
    val gl: Win32GL = Win32GL
    override fun activeTexture(texture: Int): Unit = gl.glActiveTexture(texture)
    override fun attachShader(program: Int, shader: Int): Unit = gl.glAttachShader(program, shader)
    override fun bindAttribLocation(program: Int, index: Int, name: String): Unit =
        gl.glBindAttribLocation(program, index, name)

    override fun bindBuffer(target: Int, buffer: Int): Unit = gl.glBindBuffer(target, buffer)
    override fun bindFramebuffer(target: Int, framebuffer: Int): Unit = gl.glBindFramebuffer(target, framebuffer)
    override fun bindRenderbuffer(target: Int, renderbuffer: Int): Unit = gl.glBindRenderbuffer(target, renderbuffer)
    override fun bindTexture(target: Int, texture: Int): Unit = gl.glBindTexture(target, texture)
    override fun blendColor(red: Float, green: Float, blue: Float, alpha: Float): Unit =
        gl.glBlendColor(red, green, blue, alpha)

    override fun blendEquation(mode: Int): Unit = gl.glBlendEquation(mode)
    override fun blendEquationSeparate(modeRGB: Int, modeAlpha: Int): Unit =
        gl.glBlendEquationSeparate(modeRGB, modeAlpha)

    override fun blendFunc(sfactor: Int, dfactor: Int): Unit = gl.glBlendFunc(sfactor, dfactor)
    override fun blendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int): Unit =
        gl.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha)

    override fun bufferData(target: Int, size: Int, data: FBuffer, usage: Int): Unit =
        gl.glBufferData(target, size.toLong(), data.nioBuffer, usage)

    override fun bufferSubData(target: Int, offset: Int, size: Int, data: FBuffer): Unit =
        gl.glBufferSubData(target, offset.toLong(), size.toLong(), data.nioBuffer)

    override fun checkFramebufferStatus(target: Int): Int = gl.glCheckFramebufferStatus(target)
    override fun clear(mask: Int): Unit = gl.glClear(mask)
    override fun clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit =
        gl.glClearColor(red, green, blue, alpha)

    override fun clearDepthf(d: Float): Unit = gl.glClearDepth(d.toDouble())
    override fun clearStencil(s: Int): Unit = gl.glClearStencil(s)
    override fun colorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean): Unit =
        gl.glColorMask(red, green, blue, alpha)

    override fun compileShader(shader: Int): Unit = gl.glCompileShader(shader)
    override fun compressedTexImage2D(
        target: Int,
        level: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        border: Int,
        imageSize: Int,
        data: FBuffer
    ): Unit = gl.glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data.nioBuffer)

    override fun compressedTexSubImage2D(
        target: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        imageSize: Int,
        data: FBuffer
    ): Unit =
        gl.glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data.nioBuffer)

    override fun copyTexImage2D(
        target: Int,
        level: Int,
        internalformat: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int,
        border: Int
    ): Unit = gl.glCopyTexImage2D(target, level, internalformat, x, y, width, height, border)

    override fun copyTexSubImage2D(
        target: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ): Unit = gl.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height)

    override fun createProgram(): Int = gl.glCreateProgram()
    override fun createShader(type: Int): Int = gl.glCreateShader(type)
    override fun cullFace(mode: Int): Unit = gl.glCullFace(mode)
    override fun deleteBuffers(n: Int, items: FBuffer): Unit = gl.glDeleteBuffers(n, items.nioIntBuffer)
    override fun deleteFramebuffers(n: Int, items: FBuffer): Unit = gl.glDeleteFramebuffers(n, items.nioIntBuffer)
    override fun deleteProgram(program: Int): Unit = gl.glDeleteProgram(program)
    override fun deleteRenderbuffers(n: Int, items: FBuffer): Unit = gl.glDeleteRenderbuffers(n, items.nioIntBuffer)
    override fun deleteShader(shader: Int): Unit = gl.glDeleteShader(shader)
    override fun deleteTextures(n: Int, items: FBuffer): Unit = gl.glDeleteTextures(n, items.nioIntBuffer)
    override fun depthFunc(func: Int): Unit = gl.glDepthFunc(func)
    override fun depthMask(flag: Boolean): Unit = gl.glDepthMask(flag)
    override fun depthRangef(n: Float, f: Float): Unit = gl.glDepthRange(n.toDouble(), f.toDouble())
    override fun detachShader(program: Int, shader: Int): Unit = gl.glDetachShader(program, shader)
    override fun disable(cap: Int): Unit = gl.glDisable(cap)
    override fun disableVertexAttribArray(index: Int): Unit = gl.glDisableVertexAttribArray(index)
    override fun drawArrays(mode: Int, first: Int, count: Int): Unit = gl.glDrawArrays(mode, first, count)
    override fun drawElements(mode: Int, count: Int, type: Int, indices: Int): Unit =
        gl.glDrawElements(mode, count, type, indices.toLong())

    override fun enable(cap: Int): Unit = gl.glEnable(cap)
    override fun enableVertexAttribArray(index: Int): Unit = gl.glEnableVertexAttribArray(index)
    override fun finish(): Unit = gl.glFinish()
    override fun flush(): Unit = gl.glFlush()
    override fun framebufferRenderbuffer(
        target: Int,
        attachment: Int,
        renderbuffertarget: Int,
        renderbuffer: Int
    ): Unit = gl.glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer)

    override fun framebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int, level: Int): Unit =
        gl.glFramebufferTexture2D(target, attachment, textarget, texture, level)

    override fun frontFace(mode: Int): Unit = gl.glFrontFace(mode)
    override fun genBuffers(n: Int, buffers: FBuffer): Unit = gl.glGenBuffers(n, buffers.nioIntBuffer)
    override fun generateMipmap(target: Int): Unit = gl.glGenerateMipmap(target)
    override fun genFramebuffers(n: Int, framebuffers: FBuffer): Unit =
        gl.glGenFramebuffers(n, framebuffers.nioIntBuffer)

    override fun genRenderbuffers(n: Int, renderbuffers: FBuffer): Unit =
        gl.glGenRenderbuffers(n, renderbuffers.nioIntBuffer)

    override fun genTextures(n: Int, textures: FBuffer): Unit = gl.glGenTextures(n, textures.nioIntBuffer)
    override fun getActiveAttrib(
        program: Int,
        index: Int,
        bufSize: Int,
        length: FBuffer,
        size: FBuffer,
        type: FBuffer,
        name: FBuffer
    ): Unit = gl.glGetActiveAttrib(
        program,
        index,
        bufSize,
        length.nioIntBuffer,
        size.nioIntBuffer,
        type.nioIntBuffer,
        name.nioBuffer
    )

    override fun getActiveUniform(
        program: Int,
        index: Int,
        bufSize: Int,
        length: FBuffer,
        size: FBuffer,
        type: FBuffer,
        name: FBuffer
    ): Unit = gl.glGetActiveUniform(
        program,
        index,
        bufSize,
        length.nioIntBuffer,
        size.nioIntBuffer,
        type.nioIntBuffer,
        name.nioBuffer
    )

    override fun getAttachedShaders(program: Int, maxCount: Int, count: FBuffer, shaders: FBuffer): Unit =
        gl.glGetAttachedShaders(program, maxCount, count.nioIntBuffer, shaders.nioIntBuffer)

    override fun getAttribLocation(program: Int, name: String): Int = gl.glGetAttribLocation(program, name)
    override fun getUniformLocation(program: Int, name: String): Int = gl.glGetUniformLocation(program, name)
    override fun getBooleanv(pname: Int, data: FBuffer): Unit = gl.glGetBooleanv(pname, data.nioBuffer)
    override fun getBufferParameteriv(target: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetBufferParameteriv(target, pname, params.nioIntBuffer)

    override fun getError(): Int = gl.glGetError()
    override fun getFloatv(pname: Int, data: FBuffer): Unit = gl.glGetFloatv(pname, data.nioFloatBuffer)
    override fun getFramebufferAttachmentParameteriv(target: Int, attachment: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetFramebufferAttachmentParameteriv(target, attachment, pname, params.nioIntBuffer)

    override fun getIntegerv(pname: Int, data: FBuffer): Unit = gl.glGetIntegerv(pname, data.nioIntBuffer)
    override fun getProgramInfoLog(program: Int, bufSize: Int, length: FBuffer, infoLog: FBuffer): Unit =
        gl.glGetProgramInfoLog(program, bufSize, length.nioIntBuffer, infoLog.nioBuffer)

    override fun getRenderbufferParameteriv(target: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetRenderbufferParameteriv(target, pname, params.nioIntBuffer)

    override fun getProgramiv(program: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetProgramiv(program, pname, params.nioIntBuffer)

    override fun getShaderiv(shader: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetShaderiv(shader, pname, params.nioIntBuffer)

    override fun getShaderInfoLog(shader: Int, bufSize: Int, length: FBuffer, infoLog: FBuffer): Unit =
        gl.glGetShaderInfoLog(shader, bufSize, length.nioIntBuffer, infoLog.nioBuffer)

    override fun getShaderPrecisionFormat(
        shadertype: Int,
        precisiontype: Int,
        range: FBuffer,
        precision: FBuffer
    ): Unit = gl.glGetShaderPrecisionFormat(shadertype, precisiontype, range.nioIntBuffer, precision.nioIntBuffer)

    override fun getShaderSource(shader: Int, bufSize: Int, length: FBuffer, source: FBuffer): Unit =
        gl.glGetShaderSource(shader, bufSize, length.nioIntBuffer, source.nioBuffer)

    override fun getString(name: Int): String = gl.glGetString(name)
    override fun getTexParameterfv(target: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetTexParameterfv(target, pname, params.nioFloatBuffer)

    override fun getTexParameteriv(target: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetTexParameteriv(target, pname, params.nioIntBuffer)

    override fun getUniformfv(program: Int, location: Int, params: FBuffer): Unit =
        gl.glGetUniformfv(program, location, params.nioFloatBuffer)

    override fun getUniformiv(program: Int, location: Int, params: FBuffer): Unit =
        gl.glGetUniformiv(program, location, params.nioIntBuffer)

    override fun getVertexAttribfv(index: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetVertexAttribfv(index, pname, params.nioFloatBuffer)

    override fun getVertexAttribiv(index: Int, pname: Int, params: FBuffer): Unit =
        gl.glGetVertexAttribiv(index, pname, params.nioIntBuffer)

    override fun getVertexAttribPointerv(index: Int, pname: Int, pointer: FBuffer): Unit =
        gl.glGetVertexAttribiv(index, pname, pointer.nioIntBuffer)

    override fun hint(target: Int, mode: Int): Unit = gl.glHint(target, mode)
    override fun isBuffer(buffer: Int): Boolean = gl.glIsBuffer(buffer)
    override fun isEnabled(cap: Int): Boolean = gl.glIsEnabled(cap)
    override fun isFramebuffer(framebuffer: Int): Boolean = gl.glIsFramebuffer(framebuffer)
    override fun isProgram(program: Int): Boolean = gl.glIsProgram(program)
    override fun isRenderbuffer(renderbuffer: Int): Boolean = gl.glIsRenderbuffer(renderbuffer)
    override fun isShader(shader: Int): Boolean = gl.glIsShader(shader)
    override fun isTexture(texture: Int): Boolean = gl.glIsTexture(texture)
    override fun lineWidth(width: Float): Unit = gl.glLineWidth(width)
    override fun linkProgram(program: Int): Unit = gl.glLinkProgram(program)
    override fun pixelStorei(pname: Int, param: Int): Unit = gl.glPixelStorei(pname, param)
    override fun polygonOffset(factor: Float, units: Float): Unit = gl.glPolygonOffset(factor, units)
    override fun readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: FBuffer): Unit =
        gl.glReadPixels(x, y, width, height, format, type, pixels.nioBuffer)

    override fun releaseShaderCompiler(): Unit = gl.glReleaseShaderCompiler()
    override fun renderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int): Unit =
        gl.glRenderbufferStorage(target, internalformat, width, height)

    override fun sampleCoverage(value: Float, invert: Boolean): Unit = gl.glSampleCoverage(value, invert)
    override fun scissor(x: Int, y: Int, width: Int, height: Int): Unit = gl.glScissor(x, y, width, height)
    override fun shaderBinary(count: Int, shaders: FBuffer, binaryformat: Int, binary: FBuffer, length: Int): Unit =
        gl.glShaderBinary(count, shaders.nioIntBuffer, binaryformat, binary.nioBuffer, length)

    override fun shaderSource(shader: Int, string: String): Unit =
        gl.glShaderSource(shader, 1, arrayOf(string), intArrayOf(string.length))

    override fun stencilFunc(func: Int, ref: Int, mask: Int): Unit = gl.glStencilFunc(func, ref, mask)
    override fun stencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int): Unit =
        gl.glStencilFuncSeparate(face, func, ref, mask)

    override fun stencilMask(mask: Int): Unit = gl.glStencilMask(mask)
    override fun stencilMaskSeparate(face: Int, mask: Int): Unit = gl.glStencilMaskSeparate(face, mask)
    override fun stencilOp(fail: Int, zfail: Int, zpass: Int): Unit = gl.glStencilOp(fail, zfail, zpass)
    override fun stencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int): Unit =
        gl.glStencilOpSeparate(face, sfail, dpfail, dppass)

    override fun texImage2D(
        target: Int,
        level: Int,
        internalformat: Int,
        width: Int,
        height: Int,
        border: Int,
        format: Int,
        type: Int,
        pixels: FBuffer?
    ): Unit = gl.glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels?.nioBuffer)

    override fun texImage2D(
        target: Int,
        level: Int,
        internalformat: Int,
        format: Int,
        type: Int,
        data: NativeImage
    ): Unit = gl.glTexImage2D(
        target,
        level,
        internalformat,
        data.width,
        data.height,
        0,
        format,
        type,
        (data as AwtNativeImage).buffer
    )

    override fun texParameterf(target: Int, pname: Int, param: Float): Unit = gl.glTexParameterf(target, pname, param)
    override fun texParameterfv(target: Int, pname: Int, params: FBuffer): Unit =
        gl.glTexParameterfv(target, pname, params.nioFloatBuffer)

    override fun texParameteri(target: Int, pname: Int, param: Int): Unit = gl.glTexParameteri(target, pname, param)
    override fun texParameteriv(target: Int, pname: Int, params: FBuffer): Unit =
        gl.glTexParameteriv(target, pname, params.nioIntBuffer)

    override fun texSubImage2D(
        target: Int,
        level: Int,
        xoffset: Int,
        yoffset: Int,
        width: Int,
        height: Int,
        format: Int,
        type: Int,
        pixels: FBuffer
    ): Unit = gl.glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels.nioBuffer)

    override fun uniform1f(location: Int, v0: Float): Unit = gl.glUniform1f(location, v0)
    override fun uniform1fv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform1fv(location, count, value.nioFloatBuffer)

    override fun uniform1i(location: Int, v0: Int): Unit = gl.glUniform1i(location, v0)
    override fun uniform1iv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform1iv(location, count, value.nioIntBuffer)

    override fun uniform2f(location: Int, v0: Float, v1: Float): Unit = gl.glUniform2f(location, v0, v1)
    override fun uniform2fv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform2fv(location, count, value.nioFloatBuffer)

    override fun uniform2i(location: Int, v0: Int, v1: Int): Unit = gl.glUniform2i(location, v0, v1)
    override fun uniform2iv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform2iv(location, count, value.nioIntBuffer)

    override fun uniform3f(location: Int, v0: Float, v1: Float, v2: Float): Unit = gl.glUniform3f(location, v0, v1, v2)
    override fun uniform3fv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform3fv(location, count, value.nioFloatBuffer)

    override fun uniform3i(location: Int, v0: Int, v1: Int, v2: Int): Unit = gl.glUniform3i(location, v0, v1, v2)
    override fun uniform3iv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform3iv(location, count, value.nioIntBuffer)

    override fun uniform4f(location: Int, v0: Float, v1: Float, v2: Float, v3: Float): Unit =
        gl.glUniform4f(location, v0, v1, v2, v3)

    override fun uniform4fv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform4fv(location, count, value.nioFloatBuffer)

    override fun uniform4i(location: Int, v0: Int, v1: Int, v2: Int, v3: Int): Unit =
        gl.glUniform4i(location, v0, v1, v2, v3)

    override fun uniform4iv(location: Int, count: Int, value: FBuffer): Unit =
        gl.glUniform4iv(location, count, value.nioIntBuffer)

    override fun uniformMatrix2fv(location: Int, count: Int, transpose: Boolean, value: FBuffer): Unit =
        gl.glUniformMatrix2fv(location, count, transpose, value.nioFloatBuffer)

    override fun uniformMatrix3fv(location: Int, count: Int, transpose: Boolean, value: FBuffer): Unit =
        gl.glUniformMatrix3fv(location, count, transpose, value.nioFloatBuffer)

    override fun uniformMatrix4fv(location: Int, count: Int, transpose: Boolean, value: FBuffer): Unit =
        gl.glUniformMatrix4fv(location, count, transpose, value.nioFloatBuffer)

    override fun useProgram(program: Int): Unit = gl.glUseProgram(program)
    override fun validateProgram(program: Int): Unit = gl.glValidateProgram(program)
    override fun vertexAttrib1f(index: Int, x: Float): Unit = gl.glVertexAttrib1f(index, x)
    override fun vertexAttrib1fv(index: Int, v: FBuffer): Unit = gl.glVertexAttrib1fv(index, v.nioFloatBuffer)
    override fun vertexAttrib2f(index: Int, x: Float, y: Float): Unit = gl.glVertexAttrib2f(index, x, y)
    override fun vertexAttrib2fv(index: Int, v: FBuffer): Unit = gl.glVertexAttrib2fv(index, v.nioFloatBuffer)
    override fun vertexAttrib3f(index: Int, x: Float, y: Float, z: Float): Unit = gl.glVertexAttrib3f(index, x, y, z)
    override fun vertexAttrib3fv(index: Int, v: FBuffer): Unit = gl.glVertexAttrib3fv(index, v.nioFloatBuffer)
    override fun vertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float): Unit =
        gl.glVertexAttrib4f(index, x, y, z, w)

    override fun vertexAttrib4fv(index: Int, v: FBuffer): Unit = gl.glVertexAttrib4fv(index, v.nioFloatBuffer)
    override fun vertexAttribPointer(
        index: Int,
        size: Int,
        type: Int,
        normalized: Boolean,
        stride: Int,
        pointer: Int
    ): Unit = gl.glVertexAttribPointer(index, size, type, normalized, stride, pointer.toLong())

    override fun viewport(x: Int, y: Int, width: Int, height: Int): Unit = gl.glViewport(x, y, width, height)
}
