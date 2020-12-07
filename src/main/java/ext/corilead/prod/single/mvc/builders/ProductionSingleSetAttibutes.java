package ext.corilead.prod.single.mvc.builders;


import com.ptc.core.ui.resources.ComponentType;
import com.ptc.jca.mvc.components.AbstractAttributesComponentBuilder;
import com.ptc.jca.mvc.components.JcaAttributeConfig;
import com.ptc.jca.mvc.components.JcaGroupConfig;
import ext.corilead.prod.single.ProductionSingle;
import wt.util.WTException;

import com.ptc.mvc.components.AttributePanelConfig;
import com.ptc.mvc.components.ComponentBuilder;
import com.ptc.mvc.components.ComponentConfigFactory;
import com.ptc.mvc.components.ComponentId;
import com.ptc.mvc.components.ComponentParams;
import com.ptc.mvc.components.TypeBased;
import wt.vc.baseline.ManagedBaseline;

@ComponentBuilder("corilead.prod.single.ProductionSingleSetAttibutes")
@TypeBased("ext.corilead.prod.single.ProductionSingle")
public class ProductionSingleSetAttibutes extends AbstractAttributesComponentBuilder {
    @Override
    protected AttributePanelConfig buildAttributesComponentConfig(final ComponentParams params) throws WTException {
        final ComponentConfigFactory factory = getComponentConfigFactory();
        final AttributePanelConfig panel; {
            panel = factory.newAttributePanelConfig
                    (ComponentId.ATTRIBUTE_PANEL_ID);
            panel.setComponentType
                    (ComponentType.WIZARD_ATTRIBUTES_TABLE);
            final JcaGroupConfig group; {
                group = (JcaGroupConfig) factory.newGroupConfig();
                group.setId("01");
                group.setLabel("�½�Ͷ����");
                group.setIsGridLayout(true);
                //.setLabel("���")
                group.addComponent(getAttribute(ProductionSingle.NAME,"����", factory));
                group.addComponent(getAttribute(ProductionSingle.TELEPHONE,"�绰", factory));
                group.addComponent(getAttribute(ProductionSingle.PRODUCT_CODE,"��Ʒ����", factory));
                //group.addComponent(getAttribute(ProductionSingle.PRODUCTION_UNIT,"Ͷ����λ", factory));
                //group.addComponent(getAttribute(ProductionSingle.NUMBER_OF_BACKUPS,"��������", factory));
                group.addComponent(getAttribute(ProductionSingle.KIND, "ѡ�����",factory));
                group.addComponent(getAttribute(ProductionSingle.REMARKS, "��ע",factory));
            }
            panel.addComponent(group);
        }
        return panel;
    }
    JcaAttributeConfig getAttribute
            (final String id, final String lable,final ComponentConfigFactory
                    factory) {
        final JcaAttributeConfig attribute = (JcaAttributeConfig)
                factory.newAttributeConfig();
        attribute.setId(id);
        attribute.setLabel(lable);
        return attribute;
    }
}

