package net.sonerapp.db_course_project.application.dto.PostController;

import java.util.List;

public record PostListItemDto(PostDto post, List<PostCommentDto> commmentList, int likeCount) {

}
