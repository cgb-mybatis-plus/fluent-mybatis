package cn.org.atool.fluent.mybatis.processor.filer;

import cn.org.atool.fluent.mybatis.base.EntityRefQuery;
import cn.org.atool.fluent.mybatis.processor.entity.EntityRefMethod;
import cn.org.atool.fluent.mybatis.processor.entity.FluentEntity;
import cn.org.atool.fluent.mybatis.processor.entity.FluentList;
import cn.org.atool.generator.javafile.AbstractFile;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.Map;

import static cn.org.atool.fluent.mybatis.utility.MybatisUtil.capitalFirst;
import static cn.org.atool.generator.util.ClassNames.Lombok_Getter;
import static cn.org.atool.generator.util.ClassNames.Spring_Autowired;

/**
 * Mappers 代码生成
 *
 * @author darui.wu
 */
public class RefsFile extends AbstractFile {

    private static String Refs = "Refs";

    public static ClassName getClassName() {
        return ClassName.get(FluentList.getSamePackage(), Refs);
    }

    public RefsFile() {
        this.packageName = FluentList.getSamePackage();
        this.klassName = Refs;
        this.comment = "" +
            "\n o - 查询器，更新器工厂类单例引用" +
            "\n o - 应用所有Mapper Bean引用" +
            "\n o - Entity关联对象延迟加载查询实现";
    }

    @Override
    protected void build(TypeSpec.Builder builder) {
        builder.superclass(EntityRefQuery.class);
        builder.addModifiers(Modifier.ABSTRACT);
        for (FluentEntity fluent : FluentList.getFluents()) {
            builder.addField(this.f_factory(fluent));
        }
        for (FluentEntity fluent : FluentList.getFluents()) {
            builder.addField(this.f_mapper(fluent));
        }
        builder.addMethod(this.m_initEntityMapper());
        builder.addMethod(this.m_instance());
        for (FluentEntity fluent : FluentList.getFluents()) {
            for (EntityRefMethod refField : fluent.getRefMethods()) {
                builder.addMethod(this.m_refMethod(fluent, refField));
            }
        }
    }

    private MethodSpec m_initEntityMapper() {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("initEntityMapper")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.FINAL, Modifier.PROTECTED);

        for (FluentEntity fluent : FluentList.getFluents()) {
            builder.addStatement("this.entityMappers.put($T.class, this.$LMapper)", fluent.entity(), fluent.lowerNoSuffix());
        }
        return builder.build();
    }

    private MethodSpec m_refMethod(FluentEntity fluent, EntityRefMethod refField) {
        MethodSpec methodSpec = this.m_refRealMethod(fluent, refField);
        if (methodSpec != null) {
            return methodSpec;
        }
        return MethodSpec.methodBuilder(refField.getRefMethod(fluent))
            .addParameter(fluent.entity(), "entity")
            .addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
            .returns(refField.getJavaType())
            .addJavadoc("{@link $L#$L}", fluent.getClassName(), refField.getName())
            .build();
    }

    private MethodSpec m_refRealMethod(FluentEntity fluent, EntityRefMethod refField) {
        if (!refField.isAutoMapping()) {
            return null;
        }
        FluentEntity ref = FluentList.getFluentEntity(refField.getReturnEntity());
        if (ref == null) {
            return null;
        }
        MethodSpec.Builder spec = MethodSpec.methodBuilder(refField.getRefMethod(fluent))
            .addParameter(fluent.entity(), "entity")
            .addModifiers(Modifier.PUBLIC)
            .returns(refField.getJavaType())
            .addJavadoc("{@link $L#$L}", fluent.getClassName(), refField.getName());
        String method = refField.returnList() ? "listEntity" : "findOne";
        spec.addCode("return $LMapper.$L(new $T()\n", ref.lowerNoSuffix(), method, ref.query());
        int index = 0;
        for (Map.Entry<String, String> pair : refField.getMapping().entrySet()) {
            spec.addCode(index == 0 ? "\t.where" : "\t.and")
                .addCode(".$L().eq(entity.get$L())\n", pair.getKey(), capitalFirst(pair.getValue(), ""));
            index++;
        }
        spec.addStatement("\t.end())");
        return spec.build();
    }

    private MethodSpec m_instance() {
        return MethodSpec.methodBuilder("INSTANCE")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
            .addJavadoc("Wrapper单例")
            .returns(RefsFile.getClassName())
            .addStatement("return ($L) $T.query()", Refs, EntityRefQuery.class)
            .build();
    }

    private FieldSpec f_mapper(FluentEntity fluent) {
        return FieldSpec.builder(fluent.mapper(), fluent.lowerNoSuffix() + "Mapper",
            Modifier.PROTECTED)
            .addAnnotation(Lombok_Getter)
            .addAnnotation(Spring_Autowired)
            .build();
    }

    private FieldSpec f_factory(FluentEntity fluent) {
        return FieldSpec.builder(fluent.wrapperFactory(), fluent.lowerNoSuffix() + "Default",
            Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
            .initializer("$T.INSTANCE", fluent.wrapperFactory())
            .build();
    }

    @Override
    protected boolean isInterface() {
        return false;
    }

    protected String generatorName() {
        return "FluentMybatis";
    }
}