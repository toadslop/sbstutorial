package pasona.sbstutorial.handlers.external;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sap.cds.services.handler.annotations.ServiceName;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.Result;
import com.sap.cds.ql.cqn.CqnSelect;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.cds.RemoteService;

import cds.gen.ztest_project_srv.*;

@Component
@ServiceName(ZtestProjectSrv_.CDS_NAME)
public class ExternalServiceHandler implements EventHandler {

    @Autowired
    @Qualifier(ZtestProjectSrv_.CDS_NAME)
    RemoteService remote;

    @On(event = CdsService.EVENT_READ, entity = Purchaseorders_.CDS_NAME)
    public void onRead(CdsReadEventContext context) {
        String red = "\u001B[31m";
        CqnSelect readQuery = context.getCqn();
        Result result = remote.run(readQuery);
        System.out.println(red + readQuery);
        context.setResult(result);
    }

}