package kotlin;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.MustBeDocumented;
import kotlin.annotation.Retention;
import kotlin.annotation.Target;

@MustBeDocumented
@SinceKotlin(version = "1.1")
@Target(allowedTargets = {AnnotationTarget.CLASS, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Retention(AnnotationRetention.BINARY)
@Documented
@java.lang.annotation.Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lkotlin/PublishedApi;", "", "kotlin-runtime"}, k = 1, mv = {1, 1, 6})
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
public @interface PublishedApi {
}