package org.seasar.s2csv.creator;

import org.seasar.framework.container.ComponentCustomizer;
import org.seasar.framework.container.creator.ComponentCreatorImpl;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.convention.NamingConvention;

/**
 * 決められた命名規約に従って、クラスからCsvEntityクラスのコンポーネント定義を作成します。 作成されるコンポーネント定義の各種属性は以下になります。
 * 
 * <table>
 * <tr>
 * <th>サフィックス</th>
 * <td>"Csv"</td>
 * </tr>
 * <tr>
 * <th>インスタンス定義</th>
 * <td>prototype</td>
 * </tr>
 * <tr>
 * <th>自動バインディング</th>
 * <td>auto</td>
 * </tr>
 * <tr>
 * <th>外部バインディング</th>
 * <td>無効</td>
 * </tr>
 * <tr>
 * <th>インターフェース</th>
 * <td>対象外</td>
 * </tr>
 * <tr>
 * <th>抽象クラス</th>
 * <td>対象外</td>
 * </tr>
 * </table>
 * </p>
 * 
 * @author newta
 */
public class CsvCreator extends ComponentCreatorImpl {

    /**
     * 指定された{@link NamingConvention 命名規約}に従った{@link CsvCreator}を作成します。
     * 
     * @param namingConvention
     *            命名規約
     */
    public CsvCreator(NamingConvention namingConvention) {
        super(namingConvention);
        setNameSuffix("Csv");
        setInstanceDef(InstanceDefFactory.PROTOTYPE);
    }
    

    /**
     * {@link ComponentCustomizer}を返します。
     * 
     * @return {@link ComponentCustomizer}
     */
    public ComponentCustomizer getCsvCustomizer() {
        return getCustomizer();
    }

    /**
     * {@link ComponentCustomizer}を設定します。
     * 
     * @param customizer
     *            {@link ComponentCustomizer}
     */
    public void setCsvCustomizer(ComponentCustomizer customizer) {
        setCustomizer(customizer);
    }

}
