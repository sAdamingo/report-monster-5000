package com.monsters.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;

public class FileSearcher {
    public static Collection<File> findFilesWithExtenstion(String extension, String path) {
        File dir = new File(path);

        Collection<File> files = FileUtils.listFiles(
                dir,
                new RegexFileFilter("^(.*?)" + extension),
                DirectoryFileFilter.DIRECTORY);

        for (File xmlfile : files) {
            System.out.println("Found: " + xmlfile);
        }

        return files;

    }

    public static Collection<File> getXlsFiles(String path) {
        Collection<File> xlsFiles = findFilesWithExtenstion("xls", path);
        return xlsFiles;
    }
}
