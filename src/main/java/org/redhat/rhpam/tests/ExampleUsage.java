package org.redhat.rhpam.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kie.server.api.model.definition.SearchQueryFilterSpec;
import org.kie.server.api.model.instance.ProcessInstanceUserTaskWithVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleUsage extends AbstractKieServerConnector {

    private static final String CONTAINER_ID = "SampleHTProject";
    private static final String PROCESS_ID = "SampleHTProject.MySampleProcess";
    private static final Logger log = LoggerFactory.getLogger(ExampleUsage.class);
    private static final Integer INSTANCES = 5;


    public static void main(String[] args) {
        ExampleUsage client = new ExampleUsage();

        List<Long> pids = new ArrayList<Long>();

        for (int i = 0; i < INSTANCES; i++) {
            Map<String, Object> params = new HashMap<String,Object>();
            params.put("team","Lloyds");
            pids.add(client.getProcessClient().startProcess(CONTAINER_ID, PROCESS_ID, params));
            log.info("Started process instance with id {}", pids.get(pids.size() - 1));
        }

        SearchQueryFilterSpec searchSpec = new SearchQueryFilterSpec();
        List<ProcessInstanceUserTaskWithVariables> tasks = client.getQueryClient().queryUserTaskByVariables(searchSpec,0,5);


        List<TaskDTO> taskDTOs = tasks.stream().map( t ->
            new TaskDTO(t.getProcessInstanceId(), t.getId(), (String)t.getProcessVariables().get("team"))
        ).collect(Collectors.toList());

        log.info("Found {} Tasks {}", taskDTOs.size(), taskDTOs);
        client.getProcessClient().abortProcessInstances(CONTAINER_ID,pids);

    }
}
