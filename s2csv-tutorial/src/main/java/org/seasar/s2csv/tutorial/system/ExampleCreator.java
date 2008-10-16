package org.seasar.s2csv.tutorial.system;

import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.creator.ComponentCreatorImpl;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.convention.NamingConvention;

public class ExampleCreator extends ComponentCreatorImpl {

    public ExampleCreator(NamingConvention namingConvention) {
        super(namingConvention);
        setNameSuffix("Example");
        setInstanceDef(InstanceDefFactory.PROTOTYPE);
    }
    
    public ComponentCustomizer getExampleCustomizer() {
        return getCustomizer();
    }

    public void setExampleCustomizer(ComponentCustomizer customizer) {
        setCustomizer(customizer);
    }
}
