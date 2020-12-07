
package ext.corilead.prod.single.mvc.builders;


import com.ptc.jca.mvc.components.JcaComponentParams;
import com.ptc.mvc.components.*;


import com.ptc.netmarkets.model.NmOid;
import com.ptc.netmarkets.util.beans.NmCommandBean;
import com.ptc.netmarkets.util.beans.NmHelperBean;
import wt.util.WTException;

import java.util.List;
import java.util.Map;

/*
Ͷ����������
 */
@ComponentBuilder({"ext.corilead.prod.single.mvc.builders.ProdcutionSingleDataBuilder"})
public class ProdcutionSingleDataBuilder extends AbstractComponentBuilder {


    @Override
    public Object buildComponentData(ComponentConfig componentConfig, ComponentParams componentParams) throws Exception {
        NmHelperBean nmhelperbean = ((JcaComponentParams) componentParams).getHelperBean();
        NmCommandBean nmcommandbean = nmhelperbean.getNmCommandBean();
        Map map = nmcommandbean.getText();
        List<NmOid> oidlist = nmcommandbean.getActionOidsWithWizard();
        //oidlist.get(0).get
        return null;


    }
    @Override
    public ComponentConfig buildComponentConfig(ComponentParams componentParams) throws WTException {

        ComponentConfigFactory componentConfigFactory = this.getComponentConfigFactory();
        TableConfig tableConfig = componentConfigFactory.newTableConfig();
        tableConfig.setLabel("ѡ��Ͷ������");
        tableConfig.setId("ext.corilead.prod.single.mvc.builders.ProdcutionSingleDataBuilder");
        tableConfig.setSelectable(true);
        tableConfig.setShowCount(true);
        tableConfig.setActionModel("selectSendData");
        ColumnConfig type = componentConfigFactory.newColumnConfig("type", true);
        type.setLabel("����");
        type.setId("type");
        tableConfig.addComponent(type);
        ColumnConfig number = componentConfigFactory.newColumnConfig("number", true);
        number.setLabel("���");
        number.setId("number");
        tableConfig.addComponent(number);

        ColumnConfig name = componentConfigFactory.newColumnConfig("name", true);
        name.setLabel("����");
        name.setId("name");
        name.setWidth(100);
        tableConfig.addComponent(name);

        ColumnConfig num = componentConfigFactory.newColumnConfig("productionnum", true);
        num.setLabel("Ͷ������");
        num.setId("productionnum");
        num.setDataUtilityId("numberUrlDU");
        tableConfig.addComponent(num);

        ColumnConfig productionUnit = componentConfigFactory.newColumnConfig("productionUnit", true);
        productionUnit.setLabel("Ͷ����λ");
        productionUnit.setId("productionUnit");
        productionUnit.setDataUtilityId("numberUrlDU");
        productionUnit.setWidth(200);
        tableConfig.addComponent(productionUnit);
        return tableConfig;
    }


}
