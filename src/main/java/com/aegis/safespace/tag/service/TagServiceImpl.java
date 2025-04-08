package com.aegis.safespace.tag.service;

import com.aegis.safespace.exception.ResourceNotFoundException;
import com.aegis.safespace.tag.dto.CreateTagDTO;
import com.aegis.safespace.tag.dto.UpdateTagDTO;
import com.aegis.safespace.tag.model.Tag;
import com.aegis.safespace.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getTagById(UUID id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id));
    }

    @Override
    @Transactional
    public Tag createTag(CreateTagDTO createTagDTO) {
        Tag tag = Tag.builder()
                .name(createTagDTO.getName())
                .build();

        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public Tag updateTag(UUID id, UpdateTagDTO updateTagDTO) {
        Tag existingTag = getTagById(id);

        if (updateTagDTO.getName() != null) {
            existingTag.setName(updateTagDTO.getName());
        }

        return tagRepository.save(existingTag);
    }

    @Override
    @Transactional
    public void deleteTag(UUID id) {
        if (!tagRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tag not found with id: " + id);
        }

        tagRepository.deleteById(id);
    }
}