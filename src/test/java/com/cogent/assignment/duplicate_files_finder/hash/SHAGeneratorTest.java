package com.cogent.assignment.duplicate_files_finder.hash;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class SHAGeneratorTest {

    private SHAGenerator shaGenerator = new SHAGenerator();

    @Test
    void should_create_md5_sha256_for_given_content() throws NoSuchAlgorithmException {
        String content = "Hello world!";

        String result = shaGenerator.createSHAFor(content);

        assertEquals(
            "c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a",
            result
        );
    }

    @Test
    void should_create_md5_sha256_for_given_long_String() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi tellus eros, tincidunt at sodales quis, semper et urna. Etiam ullamcorper, nisl vel pretium mattis, enim velit suscipit ante, sit amet vehicula massa augue sed tellus. Proin auctor, purus sed gravida commodo, lacus odio mattis metus, sit amet elementum est est a felis. In non tincidunt odio. Duis tincidunt laoreet libero a suscipit. Ut finibus nulla in ipsum elementum accumsan. Morbi quis mauris dignissim, molestie nulla vitae, elementum dui. Integer dignissim lectus nec tortor tristique malesuada. Maecenas sollicitudin ligula malesuada nunc sodales elementum. Maecenas eleifend, lectus non varius hendrerit, enim ante euismod enim, sodales egestas libero mi quis dui. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; In pellentesque vel est vitae aliquet. Quisque eu dui auctor, feugiat urna sed, porta velit. Ut sed tortor nec erat aliquam iaculis. Ut dui enim, tincidunt sed iaculis consequat, elementum at velit.\n" +
            "\n" +
            "In ut justo massa. Aliquam erat volutpat. Curabitur at dolor sit amet diam congue aliquam non a metus. Integer velit sem, commodo a enim id, viverra aliquam nunc. Proin mollis mauris sed nunc sodales, et consequat velit vehicula. Integer nec fermentum felis. Sed a sodales libero, non volutpat erat.\n" +
            "\n" +
            "Duis eu feugiat diam. Vestibulum posuere sagittis dui, eget pretium eros eleifend quis. Integer eget sapien odio. Vestibulum venenatis scelerisque auctor. Phasellus vel lobortis arcu, at lacinia augue. Sed odio dolor, pellentesque sed feugiat vel, suscipit ut dolor. Suspendisse mollis ligula ut nunc fringilla congue. In convallis venenatis leo. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.\n" +
            "\n" +
            "Maecenas sit amet tortor vel lacus mattis vestibulum vitae ut erat. Fusce turpis quam, mollis sed odio et, semper semper lectus. Ut imperdiet mi mi, at semper nisl varius sit amet. Ut orci risus, rutrum eu pharetra ut, convallis nec lectus. Cras dapibus sodales sapien sit amet placerat. Sed dictum euismod sapien, nec viverra ligula euismod a. Curabitur accumsan, ante non auctor sagittis, metus nunc ullamcorper tortor, vitae mattis enim felis eget mi. Sed imperdiet felis non feugiat porttitor. Cras hendrerit finibus ex ut aliquam. Nulla interdum hendrerit risus, pulvinar pharetra eros mollis non. Praesent auctor convallis dictum.\n" +
            "\n" +
            "Ut dignissim orci vitae nunc varius mattis. Suspendisse facilisis nulla quam, facilisis aliquam ex commodo eu. Suspendisse potenti. Duis tristique porta ligula vitae elementum. Etiam id volutpat neque. Phasellus vulputate sed eros et egestas. Cras pharetra mauris et consectetur consectetur. Nullam ipsum lacus, vulputate ut massa ut, scelerisque porta augue. Vestibulum interdum enim non elit tempor consequat. Donec luctus nulla non lorem vehicula sollicitudin. Aliquam eget libero ligula. Ut porttitor pulvinar aliquet. Fusce quis orci non urna pharetra tempus vitae quis purus.";

        String result = shaGenerator.createSHAFor(content);

        assertEquals(
            "c0535e4be2b79ffd93291305436bf889314e4a3faec05ecffcbb7df31ad9e51a",
            result
        );
    }
}