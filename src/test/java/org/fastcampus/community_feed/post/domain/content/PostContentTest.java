package org.fastcampus.community_feed.post.domain.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {
    @Test
    void givenContentLengthIsOkWhenCreatePostContentThenReturnTextContext() {
        // given
        String contentText = "this is a test content";

        // when
        PostContent content = new PostContent(contentText);

        // then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOverLimitCreatePostContentThenThrowError() {
        // given
        // 임의 문자열을 만드는데 도움이되는 메서드 `repeat`(java 11부터 지원)
        String content = "a".repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }
    // @ParameterizedTest
    //  - 테스트를 실행할 때, JUnit에 파라미터를 주입할 테스트임을 알리는 애너테이션
    //  - @ValueSource와 같은 어노테이션이랑 함께사용
    //  - 자동으로 배열의 크기만큼 값을 주입해서 테스트를 진행해줌
    @ParameterizedTest
    @ValueSource(strings = {"뷁", "닭", "굵", "슳"})
    void givenContentLengthIsOverLimitAndKoreanCreatePostContentThenThrowError(String koreanContent) {
        // given
        String content = koreanContent.repeat(501);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUnderLimitCreatePostContentThenThrowError() {
        // given
        String content = "abcd";

        // when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyAndNullLimitCreatePostContentThenThrowError(String content) {
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    // 강의에서 PostContent 객체 생성처럼 반복적인 내용을 할때 AI(강의에선 Copilot 사용) 난 Claude 사용
    // 아래 givenContentLengthIsOkWhenUpdateContentThenNotThrowError 메서드 그냥 이름만 바꿈
    @Test
    void givenContentLengthIsOkWhenUpdateContentThenExecute(){
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when
        String newContent = "this is an other test content";
        postContent.updateContent(newContent);

        // then
        assertEquals(newContent, postContent.getContentText());
        assertTrue(postContent.isEdited());
    }
    @Test
    void givenContentLengthIsOkWhenUpdateContentThenNotThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when
        String newContent = "this is an other test content";
        postContent.updateContent(newContent);

        // then
        assertEquals(newContent, postContent.getContentText());
        assertTrue(postContent.isEdited());
    }

    @Test
    void givenContentLengthIsOverLimitWhenUpdateContentThenThrowError() {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        String overLimitContent = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(overLimitContent));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsOverLimitAndKoreanWhenUpdateContentThenThrowError(String nullOrEmptyContent) {
        // given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(nullOrEmptyContent));
    }
}
