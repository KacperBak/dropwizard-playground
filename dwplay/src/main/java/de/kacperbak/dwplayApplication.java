package de.kacperbak;

import de.kacperbak.resources.HelloWorldResource;
import de.kacperbak.health.TemplateHealthCheck;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class dwplayApplication extends Application<dwplayConfiguration> {

    public static void main(final String[] args) throws Exception {
        new dwplayApplication().run(args);
    }

    @Override
    public String getName() {
        return "dwplay";
    }

    @Override
    public void initialize(final Bootstrap<dwplayConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final dwplayConfiguration configuration, final Environment environment) {

        // resources
        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),
                configuration.getDefaultName());
        environment.jersey().register(resource);

        // healthChecks
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
