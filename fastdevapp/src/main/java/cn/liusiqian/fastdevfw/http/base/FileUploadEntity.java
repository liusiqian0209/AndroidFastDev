package cn.liusiqian.fastdevfw.http.base;

import java.io.File;

/**
 * Créé par liusiqian 16/7/14.
 */
public class FileUploadEntity
{
    String key;
    String fileName;
    String contentType;
    File file;

    public FileUploadEntity(File file)
    {
        init("file", file.getName(), null, file);
    }

    public FileUploadEntity(String key, String fileName, String contentType, File file)
    {
        init(key, fileName, contentType, file);
    }

    private void init(String key, String fileName, String contentType, File file)
    {
        this.key = key;
        this.fileName = fileName;
        this.contentType = contentType;
        this.file = file;
    }
}
