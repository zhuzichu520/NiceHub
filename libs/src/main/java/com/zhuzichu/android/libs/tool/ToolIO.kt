@file: JvmName("ToolIO")

package com.zhuzichu.android.libs.tool

import java.io.*
import java.util.ArrayList

/**
 * file结束tag
 */
const val EOF = -1

/**
 * 默认buffer大小
 */
const val DEFAULT_BUFFER_SIZE = 1024 * 4

/**
 * 换行符
 */
const val LINE_SEPARATOR_DEFAULT = "\r\n"

/**
 * 关闭一个可关闭的流对象
 *
 * @param closeable 可关闭的对象，实现Closeable接口，该对象可能为空或已关闭
 */
fun closeQuietly(closeable: Closeable?) {
    try {
        closeable?.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

/**
 * 关闭多个可关闭的流对象
 *
 * @param closeables 可关闭的对象组，实现Closeable接口，其中的对象可能已关闭
 */
fun closeQuietly(vararg closeables: Closeable) {
    for (closeable in closeables) {
        try {
            closeable.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

/**
 * 比较2个输入流是否相等
 *
 * @param in1 第一个输入流
 * @param in2 第二个输入流
 * @return 返回比较结果，true 相等
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun contentEquals(in1: InputStream?, in2: InputStream?): Boolean {
    var input1 = in1
    var input2 = in2
    if (input1 === input2) {
        return true
    }
    if (input1 == null || input2 == null) {
        return false
    }
    if (input1 !is BufferedInputStream) {
        input1 = BufferedInputStream(input1)
    }
    if (input2 !is BufferedInputStream) {
        input2 = BufferedInputStream(input2)
    }

    var ch = input1.read()
    while (EOF != ch) {
        val ch2 = input2.read()
        if (ch != ch2) {
            return false
        }
        ch = input1.read()
    }

    val ch2 = input2.read()
    return ch2 == EOF
}

/**
 * 比较2个字符输入流是否相等
 *
 * @param in1 第一个字符输入流
 * @param in2 第二个字符输入流
 * @return 返回比较结果，true 相等
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun contentEquals(in1: Reader?, in2: Reader?): Boolean {
    var input1 = in1
    var input2 = in2
    if (input1 === input2) {
        return true
    }
    if (input1 == null || input2 == null) {
        return false
    }
    input1 = buffer(input1)
    input2 = buffer(input2)

    var ch = input1.read()
    while (EOF != ch) {
        val ch2 = input2.read()
        if (ch != ch2) {
            return false
        }
        ch = input1.read()
    }

    val ch2 = input2.read()
    return ch2 == EOF
}

/**
 * 比较2个字符输入流是否相等（忽略结尾处的EOL符）
 *
 * @param input1 第一个字符输入流
 * @param input2 第二个字符输入流
 * @return 返回比较结果，true 相等
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun contentEqualsIgnoreEOL(input1: Reader?, input2: Reader?): Boolean {
    if (input1 === input2) {
        return true
    }
    if (input1 == null || input2 == null) {
        return false
    }
    val br1 = buffer(input1)
    val br2 = buffer(input2)

    var line1: String? = br1.readLine()
    var line2: String? = br2.readLine()
    while (line1 != null && line2 != null && line1 == line2) {
        line1 = br1.readLine()
        line2 = br2.readLine()
    }
    return if (line1 == null) line2 == null else line1 == line2
}


/**
 * Reader 转 BufferedReader
 *
 * @param reader 传入的Reader
 * @return 返回一个BufferedReader对象
 */
fun buffer(reader: Reader): BufferedReader {
    return (reader as? BufferedReader) ?: BufferedReader(reader)
}

/**
 * Reader 转 带Input-buffer大小的BufferedReader
 *
 * @param reader 传入的Reader
 * @param size   传入的buffer大小
 * @return 返回一个BufferedReader对象，
 * 如果传入的Reader不是BufferedReader，则返回的BufferedReader的buffer大小为传入的buffer大小
 */
fun buffer(reader: Reader, size: Int): BufferedReader {
    return (reader as? BufferedReader) ?: BufferedReader(reader, size)
}

/**
 * Writer 转 BufferedWriter
 *
 * @param writer 传入的writer对象
 * @return 如果传入的Writer已经是BufferedWriter，则返回自身，否则根据传入的writer新创建一个BufferedWriter对象
 */
fun buffer(writer: Writer): BufferedWriter {
    return (writer as? BufferedWriter) ?: BufferedWriter(writer)
}

/**
 * Writer 转 BufferedWriter
 *
 * @param writer 传入的writer对象
 * @param size   传入的buffer大小
 * @return 返回BufferedWriter对象
 * 如果传入的Writer不是bufferedWriter，则返回的bufferedWriter的buffer大小为传入的buffer大小
 */
fun buffer(writer: Writer, size: Int): BufferedWriter {
    return (writer as? BufferedWriter) ?: BufferedWriter(writer, size)
}

/**
 * OutputStream 转 BufferedOutputStream
 *
 * @param outputStream 传入的outputStream对象
 * @return 返回BufferedOutputStream对象
 */
fun buffer(outputStream: OutputStream): BufferedOutputStream {
    return (outputStream as? BufferedOutputStream) ?: BufferedOutputStream(outputStream)
}

/**
 * OutputStream 转 BufferedOutputStream
 *
 * @param outputStream 传入的outputStream对象
 * @param size         传入的buffer大小
 * @return 返回BufferedOutputStream对象
 * 如果传入的outputStream不是BufferedOutputStream，则返回的BufferedOutputStream的buffer大小为传入的buffer大小
 */
fun buffer(outputStream: OutputStream, size: Int): BufferedOutputStream {
    return (outputStream as? BufferedOutputStream) ?: BufferedOutputStream(outputStream, size)
}

/**
 * InputStream 转 BufferedInputStream
 *
 * @param inputStream 传入的inputStream对象
 * @return 返回BufferedInputStream对象
 */
fun buffer(inputStream: InputStream): BufferedInputStream {
    return (inputStream as? BufferedInputStream) ?: BufferedInputStream(inputStream)
}

/**
 * InputStream 转 BufferedInputStream
 *
 * @param inputStream 传入的inputStream对象
 * @param size        传入的buffer大小
 * @return 返回BufferedInputStream对象
 * 如果传入的InputStream不是BufferedInputStream，则返回的BufferedInputStream的buffer大小为传入的buffer大小
 */
fun buffer(inputStream: InputStream, size: Int): BufferedInputStream {
    return (inputStream as? BufferedInputStream) ?: BufferedInputStream(inputStream, size)
}

/**
 * InputStream 转 String 使用默认编码格式(UTF-8)
 *
 * @param input 要读取的输入流
 * @return String字符串
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun toString(input: InputStream): String {
    val bufferInput = BufferedReader(InputStreamReader(input))
    val stringBuilder = StringBuilder()
    var line: String?
    while ((bufferInput.readLine().also { line = it }) != null) {
        stringBuilder.append(line)
    }
    return stringBuilder.toString()
}

/**
 * Reader 转 String
 *
 * @param input 要读取的字符输入流
 * @return String字符串
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun toString(input: Reader): String {
    val r = buffer(input)
    val stringBuilder = StringBuilder()
    var line: String?
    while ((r.readLine().also { line = it }) != null) {
        stringBuilder.append(line)
        stringBuilder.append(LINE_SEPARATOR_DEFAULT)
    }
    return stringBuilder.toString()
}

/**
 * InputStream 转 byte[]
 *
 * @param input 要读取的输入流
 * @return 返回非空的byte数组
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun toByteArray(input: InputStream): ByteArray {
    ByteArrayOutputStream().use { output ->
        copy(input, output)
        return output.toByteArray()
    }
}

/**
 * InputStream 转 byte[]
 *
 * @param input 要读取的输入流
 * @param size  从输入流中要获取的数组长度
 * @return 返回非空的byte数组
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun toByteArray(input: InputStream, size: Int): ByteArray {

    if (size < 0) {
        throw IllegalArgumentException("Size must be equal or greater than zero: $size")
    }

    if (size == 0) {
        return ByteArray(0)
    }

    val data = ByteArray(size)
    var offset = 0
    var read = 0

    while (offset < size && (input.read(data, offset, size - offset).also { read = it }) != EOF) {
        offset += read
    }

    if (offset == 0) {
        return ByteArray(0)
    }

    if (offset != size) {
        throw IOException("Unexpected read size. current: $offset, expected: $size")
    }

    return data
}

/**
 * Reader 转 byte[]
 *
 * @param input 要读取的字符输入流
 * @return 返回非空的byte数组
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun toByteArray(input: Reader): ByteArray {
    ByteArrayOutputStream().use { output ->
        copy(input, output)
        return output.toByteArray()
    }
}

/**
 * 拷贝 从一个输入流拷贝到一个输出流中
 *
 * @param input  要读取的输入流
 * @param output 待写入的输出流
 * @return 拷贝的大小
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(input: InputStream, output: OutputStream): Long {
    return copy(input, output, DEFAULT_BUFFER_SIZE)
}

/**
 * 拷贝 从一个输入流拷贝到一个输出流中
 *
 * @param input      要读取的输入流
 * @param output     待写入的输出流
 * @param bufferSize buffer大小
 * @return 拷贝的大小
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(input: InputStream, output: OutputStream, bufferSize: Int): Long {
    return copy(input, output, ByteArray(bufferSize))
}

/**
 * 拷贝 从一个输入流InputStream拷贝到一个输出流OutputStream中
 *
 * @param input  要读取的输入流
 * @param output 待写入的输出流
 * @param buffer 传入的缓冲区间
 * @return 拷贝的大小
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(input: InputStream, output: OutputStream, buffer: ByteArray): Long {
    var count: Long = 0
    var n: Int
    while (EOF != (input.read(buffer).also { n = it })) {
        output.write(buffer, 0, n)
        count += n.toLong()
    }
    return count
}

/**
 * 拷贝 从一个输入流InputStream拷贝到一个Writer中
 *
 * @param input  要读取的输入流
 * @param writer 待写入的输出流Writer
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(input: InputStream, writer: Writer) {
    val reader = InputStreamReader(input)
    copy(reader, writer)
}

/**
 * 拷贝 从一个输入流Reader拷贝到一个Writer中
 *
 * @param reader 要读取的输入流
 * @param writer 待写入的输出流Writer
 * @return 返回拷贝的流大小
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(reader: Reader, writer: Writer): Long {
    return copy(reader, writer, CharArray(DEFAULT_BUFFER_SIZE))
}

/**
 * 拷贝 从一个输入流拷贝到一个输出流中
 *
 * @param reader     要读取的字符输入流
 * @param writer     待写入的字符输出流
 * @param bufferSize buffer大小
 * @return 拷贝的大小
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(reader: Reader, writer: Writer, bufferSize: Int): Long {
    return copy(reader, writer, CharArray(bufferSize))
}

/**
 * 拷贝 从一个字符输入流Reader拷贝到一个Writer中
 *
 * @param input  要读取的输入流Reader
 * @param output 待写入的输出流Writer
 * @param buffer 拷贝时使用的buffer
 * @return 拷贝的字符数目
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(input: Reader, output: Writer, buffer: CharArray): Long {
    var count: Long = 0
    var n: Int
    while (EOF != (input.read(buffer).also { n = it })) {
        output.write(buffer, 0, n)
        count += n.toLong()
    }
    return count
}

/**
 * 拷贝 从一个字符输入流Reader拷贝到一个字节输出流OutputStream中
 *
 * @param reader 要读取的输入流Reader
 * @param output 待写入的输出流OutputStream
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun copy(reader: Reader, output: OutputStream) {
    val out = OutputStreamWriter(output)
    copy(reader, out)
    // XXX Unless anyone is planning on rewriting OutputStreamWriter,
    // we have to flush here.
    out.flush()
}


/**
 * 从Reader中读取字符并用char数组记录读取的字符
 *
 * @param input  要读取的输入流Reader
 * @param buffer 记录字符的目标数组
 * @return 实际读取的字符长度
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun read(input: Reader, buffer: CharArray): Int {
    var remaining = buffer.size
    while (remaining > 0) {
        val count = input.read(buffer, 0, buffer.size)
        if (EOF == count) {
            // EOF
            break
        }
        remaining -= count
    }
    return buffer.size - remaining
}

/**
 * 从Reader中读取字节并用byte数组记录读取的字节
 *
 * @param input  要读取的输入流InputStream
 * @param buffer 记录字节的目标数组
 * @return 实际读取的byte长度
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun read(input: InputStream, buffer: ByteArray): Int {
    var remaining = buffer.size
    while (remaining > 0) {
        val count = input.read(buffer, 0, buffer.size)
        if (EOF == count) {
            // EOF
            break
        }
        remaining -= count
    }
    return buffer.size - remaining
}

/**
 * 读取一个输入流InputStream中的内容
 *
 * @param input 要读取的输入流InputStream
 * @return 返回一个非空的String集合
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun readLines(input: InputStream): List<String> {
    val reader = InputStreamReader(input)
    return readLines(reader)
}

/**
 * 读取一个字符输入流Reader中的内容
 *
 * @param input 要读取的字符输入流Reader
 * @return 返回一个非空的String集合
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun readLines(input: Reader): List<String> {
    val reader = buffer(input)
    val list = ArrayList<String>()
    var line: String? = reader.readLine()
    while (line != null) {
        list.add(line)
        line = reader.readLine()
    }
    return list
}


/**
 * 将一个byte数组内容写入一个输出流OutputStream中
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun write(data: ByteArray?, output: OutputStream) {
    if (data != null) {
        output.write(data)
    }
}

/**
 * 将一个byte数组内容分块写入一个输出流OutputStream中，写入过程中每次允许写入的最大长度不超过 1024*4 <br></br>
 * 适用于写入大量数据
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun writeChunked(data: ByteArray?, output: OutputStream) {
    if (data != null) {
        var bytes = data.size
        var offset = 0
        while (bytes > 0) {
            val chunk = Math.min(bytes, DEFAULT_BUFFER_SIZE)
            output.write(data, offset, chunk)
            bytes -= chunk
            offset += chunk
        }
    }
}

/**
 * 将一个byte数组内容写入一个字符输出流Writer中
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun write(data: ByteArray?, output: Writer) {
    if (data != null) {
        output.write(String(data))
    }
}

/**
 * 将一个char数组内容写入一个字符输出流Writer中
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun write(data: CharArray?, output: Writer) {
    if (data != null) {
        output.write(data)
    }
}

/**
 * 将一个char数组内容分块写入一个输出流Writer中，写入过程中每次允许写入的最大长度不超过 1024*4 <br></br>
 * 适用于写入大量数据
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun writeChunked(data: CharArray?, output: Writer) {
    if (data != null) {
        var bytes = data.size
        var offset = 0
        while (bytes > 0) {
            val chunk = Math.min(bytes, DEFAULT_BUFFER_SIZE)
            output.write(data, offset, chunk)
            bytes -= chunk
            offset += chunk
        }
    }
}

/**
 * 将一个char数组内容写入一个输出流OutputStream中
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun write(data: CharArray?, output: OutputStream) {
    if (data != null) {
        output.write(String(data).toByteArray())
    }
}

/**
 * 将一个String对象写入一个字符输出流Writer中
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun write(data: String?, output: Writer) {
    if (data != null) {
        output.write(data)
    }
}

/**
 * 将一个String对象写入一个输出流OutputStream中
 *
 * @param data   数据源byte数组
 * @param output 待写入的输出流
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun write(data: String?, output: OutputStream) {
    if (data != null) {
        output.write(data.toByteArray())
    }
}

/**
 * 将一个集合中的内容写入字符输出流Writer中，依次写入集合中对应item的toString内容
 *
 * @param lines      要写的数据集合
 * @param lineEnding 行分割符，传入空时使用"\n"
 * @param output     待写入的输出流OutputStream
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun writeLines(lines: Collection<*>?, lineEnding: String?, output: OutputStream) {
    var ending = lineEnding
    if (lines == null) {
        return
    }
    if (ending == null) {
        ending = LINE_SEPARATOR_DEFAULT
    }
    for (line in lines) {
        if (line != null) {
            output.write(line.toString().toByteArray())
        }
        output.write(ending.toByteArray())
    }
}

/**
 * 将一个集合中的内容写入字符输出流Writer中，依次写入集合中对应item的toString内容
 *
 * @param lines      要写的数据集合
 * @param lineEnding 行分割符，传入空时使用"\n"
 * @param writer     待写入的字符输出流Writer
 * @throws IOException IO异常
 */
@Throws(IOException::class)
fun writeLines(lines: Collection<*>?, lineEnding: String?, writer: Writer) {
    var ending = lineEnding
    if (lines == null) {
        return
    }
    if (ending == null) {
        ending = LINE_SEPARATOR_DEFAULT
    }
    for (line in lines) {
        if (line != null) {
            writer.write(line.toString())
        }
        writer.write(ending)
    }
}