package com.demo;

import com.demo.adapters.*;
import org.camunda.bpm.consulting.process_test_coverage.ProcessTestCoverage;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.engine.variable.Variables;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.assertThat;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineAssertions.processEngine;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.complete;
import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.task;

/**
 * Created by cleophas on 2018/07/22.
 */

@Deployment(resources = "BookingProcess.bpmn")
public class OrderProcessingSagaApplicationTest {

    @Before
    public void setUp() {
        Mocks.register("bookFlight", new BookFlight());
        Mocks.register("bookHotel", new BookHotel());
        Mocks.register("cancelCar", new CancelCar());
        Mocks.register("cancelFlight", new CancelFlight());
        Mocks.register("cancelHotel", new CancelHotel());
        Mocks.register("reserveCar", new ReserveCar());
    }

    @Rule
    public ProcessEngineRule processEngineRule = new ProcessEngineRule();

    @Test
    public void orderRejectPath() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("correlationId", UUID.randomUUID().toString());
        variables.put("email", "cleophasmashiri@gmail.com");
        variables.put("requireManagerApproval", true);

        ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("BookingProcessEngine", variables);

        System.out.println("Saga booking process instance: " + processInstance.getProcessInstanceId());

        assertThat(processInstance)
                .isStarted()
                .isWaitingAt("user_task_manager_approval_required")
                .task()
                .isAssignedTo("demo");
        complete(task(), Variables.createVariables().putValue("approved", Boolean.FALSE));

        assertThat(processInstance)
                .isEnded()
                .hasPassed("user_task_manager_approval_required", "manager_declines_end_process");

        ProcessTestCoverage.calculate(processInstance, processEngineRule.getProcessEngine());
    }

    @Test
    public void orderHappyPath() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("correlationId", UUID.randomUUID().toString());
        variables.put("email", "cleophas@gmail.com");
        variables.put("requireManagerApproval", false);

        ProcessInstance processInstance = processEngine().getRuntimeService().startProcessInstanceByKey("BookingProcessEngine", variables);

        System.out.println("Happy path BookingProcess: " + processInstance.getProcessInstanceId());

        assertThat(processInstance)
                .isStarted();

        assertThat(processInstance)
                .isEnded()
                .hasPassed("reserve_car_step", "book_hotel_step", "book_flight_step", "cancel_hotel_step", "cancel_car_step");

    }

    @After
    public void calculateTestCoverage() {
        ProcessTestCoverage.calculate(processEngineRule.getProcessEngine());
    }


}
