package com.juandiegoespinosasantos.challenges.reactive_programming.adapters;

import com.juandiegoespinosasantos.challenges.reactive_programming.services.IStudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author juandiegoespinosasantos@gmail.com
 * @version Aug 22, 2023
 * @since 17
 */
@ExtendWith(SpringExtension.class)
class StudentAdapterTest {

    @Mock
    private IStudentService mockService;

    private StudentAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new StudentAdapter(mockService);
    }

    // TODO
}