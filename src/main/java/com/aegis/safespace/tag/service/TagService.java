package com.aegis.safespace.tag.service;

import com.aegis.safespace.tag.dto.CreateTagDTO;
import com.aegis.safespace.tag.dto.UpdateTagDTO;
import com.aegis.safespace.tag.model.Tag;

import java.util.List;
import java.util.UUID;

public interface TagService {

    List<Tag> getAllTags();

    Tag getTagById(UUID id);

    Tag createTag(CreateTagDTO createTagDTO);

    Tag updateTag(UUID id, UpdateTagDTO updateTagDTO);

    void deleteTag(UUID id);
}

