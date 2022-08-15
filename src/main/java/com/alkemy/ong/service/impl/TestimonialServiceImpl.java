package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.response.TestimonialPageResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.service.mapper.TestimonialMapper;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private TestimonialMapper testimonialMapper;
    
    @Autowired
    private MessageSource messageSource;

    @Override
    public TestimonialDto createTestimonial(TestimonialDto testimonialDto) {
        Testimonial testimonialEntity = testimonialMapper.testimonialDtoToTestimonial(testimonialDto);
        Testimonial testimonial = testimonialRepository.save(testimonialEntity);
        return testimonialMapper.testimonialToTestimonialDto(testimonial);
    }

    @Override
    public TestimonialDto updateTestimonial(TestimonialDto testimonialDto, Long id) {
        Testimonial testimonial = testimonialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Testimonial", "idTestimonial", id));

        testimonialRepository.save(testimonial);
        Testimonial testimonialEntity = testimonialMapper.testimonialDtoToTestimonial(testimonialDto);
      return testimonialMapper.testimonialToTestimonialDto(testimonialEntity);
    }

    @Override
    public TestimonialPageResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =  sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Testimonial> testimonials = testimonialRepository.findAll(pageable);

        List<Testimonial> listOfTestomonials = testimonials.getContent();

        List<TestimonialDto> content = listOfTestomonials
                .stream()
                .map(testimonial -> testimonialMapper.testimonialToTestimonialDto(testimonial))
                .collect(Collectors.toList());

        TestimonialPageResponse testimonialPageResponse = new TestimonialPageResponse();
        testimonialPageResponse.setContent(content);
        testimonialPageResponse.setPageNo(testimonials.getNumber());
        testimonialPageResponse.setPageSize(testimonials.getSize());
        testimonialPageResponse.setTotalElements(testimonials.getTotalElements());
        testimonialPageResponse.setTotalPages(testimonials.getTotalPages());
        testimonialPageResponse.setLastPage(testimonials.isLast());
        testimonialPageResponse.setFirstPage(testimonials.isFirst());
        testimonialPageResponse.setNextPage(testimonials.nextOrLastPageable());
        testimonialPageResponse.setPreviousPage(testimonials.previousOrFirstPageable());

        return testimonialPageResponse;
    }

    @Override
    public void deleteTestimonial(Long id) {
        Optional<Testimonial> response = testimonialRepository.findById(id);
        if (response.isPresent()) {
           testimonialRepository.deleteById(id);
        }else{
            throw new EntityNotFoundException(messageSource.getMessage("testimonial.notFound", null, Locale.US));
        }
        
    }
}
