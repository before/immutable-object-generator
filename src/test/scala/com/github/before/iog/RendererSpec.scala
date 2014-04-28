package com.github.before.iog

import org.specs2.mutable._
import Renderer._

class RendererSpec extends Specification {

  "Import" should {
    "validate that package must not be empty" in {
      Import(Package(Seq()), "some") must throwA(new IllegalArgumentException("requirement failed: package must not be empty"))
    }
    "validate that name must not be empty" in {
      Import(Package(Seq("org", "github")), "") must throwA(new IllegalArgumentException("requirement failed: name must not be empty"))
    }
    "validates successfully" in {
      Import(Package(Seq("org", "github", "before")), "Test") must equalTo(Import(Package(Seq("org", "github", "before")), "Test"))
    }
    "validates successfully by using a fully qualified name" in {
      Import.fullyQualifiedName("org.github.before.Test") must equalTo(Import(Package(Seq("org", "github", "before")), "Test"))
    }
  }

  "Renderer" should {
    "render annotations" in {
      render(Annotation(Package(Seq()), "Nullable")) must equalTo("@Nullable")
      render(Annotation(Package(Seq("javax", "annotation")), "Nonnull")) must equalTo("@Nonnull")
    }
    "render imports" in {
      render(Import(Package(Seq("javax", "annotation")), "Nonnull")) must equalTo("import javax.annotation.Nonnull;")
    }
    "render packages" in {
      render(Package(Seq())) must equalTo("")
      render(Package(Seq("javax", "annotation"))) must equalTo("package javax.annotation")
    }
    "render primitives" in {
      render(Boolean) must equalTo("boolean")
      render(Byte) must equalTo("byte")
      render(Char) must equalTo("char")
      render(Double) must equalTo("double")
      render(Float) must equalTo("float")
      render(Int) must equalTo("int")
      render(Long) must equalTo("long")
      render(Short) must equalTo("short")
    }
    "render void" in {
      render(Void) must equalTo("void")
    }
  }

}