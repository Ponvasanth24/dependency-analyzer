package com.example.plugins;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.util.Set;

@Mojo(
        name = "analyze",
        defaultPhase = LifecyclePhase.COMPILE,
        requiresDependencyResolution = ResolutionScope.RUNTIME
)
public class DependencyAnalyzerMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {
        getLog().info("🔍 Listing project dependencies...");
        Set<Artifact> artifacts = project.getArtifacts();

        if (artifacts.isEmpty()) {
            getLog().info("✅ No resolved dependencies found.");
            return;
        }

        for (Artifact artifact : artifacts) {
            getLog().info("📦 " + artifact.getGroupId() + ":" + artifact.getArtifactId() + ":" + artifact.getVersion() + " [" + artifact.getScope() + "]");
        }
    }
}
