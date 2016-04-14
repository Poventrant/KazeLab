package com.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class IoTest {
    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.println(IoTest.class.getResource("/applicationContext.xml").getFile());

        System.out.println("-----------------------------Read the lines of a UTF-8 text file------------------------------------");
        File file = new File(IoTest.class.getResource("/applicationContext.xml").getFile());
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("-----------------------------Count distinct word occurrences in a file------------------------------------");
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.WHITESPACE)
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        for (String line : lines) {
            System.out.println(line);
        }

        System.out.println("-----------------------------SHA-1 a file------------------------------------");
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());
        System.out.println(hash);

        System.out.println("--------------------------Copy the data from a URL to a file---------------------------------");
        URL url = new URL("www.baidu.com");
        Resources.asByteSource(url).copyTo(Files.asByteSink(file));
    }
}
