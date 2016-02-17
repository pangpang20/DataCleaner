/**
 * DataCleaner (community edition)
 * Copyright (C) 2014 Neopost - Customer Information Management
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.datacleaner.server;

import org.apache.hadoop.conf.Configuration;

/**
 * Environment based configuration
 */
public class EnvironmentBasedHadoopClusterInformation extends DirectoryBasedHadoopClusterInformation
        implements HadoopClusterInformation {
    public static final String YARN_CONF_DIR = "YARN_CONF_DIR";
    public static final String HADOOP_CONF_DIR = "HADOOP_CONF_DIR";
    private static final String[] CONFIGURATION_DIRECTORIES =
            { System.getenv(HADOOP_CONF_DIR), System.getenv(YARN_CONF_DIR) };

    public EnvironmentBasedHadoopClusterInformation(final String name, final String description) {
        super(name, description, CONFIGURATION_DIRECTORIES);
    }

    @Override
    public Configuration getConfiguration() {
        try {
            return super.getConfiguration();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(
                    "None of the standard Hadoop environment variables has been set.", e);
        }
    }
}
