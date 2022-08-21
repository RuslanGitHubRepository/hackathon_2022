package com.infomaximum.hackaton.mapper;

import com.infomaximum.hackaton.model.comment.Comment;
import com.infomaximum.hackaton.model.comment.CommentDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment commentDtoToComment(CommentDto commentDto);
    CommentDto commentToCommentDto(Comment comment);
    ArrayList<CommentDto> commentListToCommentDtoList(List<Comment> comment);
    Set<CommentDto> commentSetToCommentDtoSet(Set<Comment> comment);
}
