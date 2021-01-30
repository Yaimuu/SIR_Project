/**
 * SettingsController : Default settings
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

public class SettingsController implements Controller
{
    /**
     * Beta configuration
     */
    public static final double minBetaMap = 0;
    public static final double maxBetaMap = 1;
    public static final double minBetaGraph = 0.0001;
    public static final double maxBetaGraph = 0.01;

    /**
     * Gamma configuration
     */
    public static final double minRecoveryTimeMap = 60;
    public static final double maxRecoveryTimeMap = 600;
    public static final double minRecoveryTimeGraph = 0;
    public static final double maxRecoveryTimeGraph = 1;
    public static final double defaultRecoveryTimeMap = 150;
    public static final double defaultRecoveryTimeGraph = 0.2;

    /**
     * Alpha configuration
     */
    public static final double minAlphaMap = 100;
    public static final double maxAlphaMap = 300;
    public static final double minAlphaGraph = 0;
    public static final double maxAlphaGraph = 1;
    public static final double defaultAlphaMap = 100;
    public static final double defaultAlphaGraph = 0.02;

    /**
     * Default spatialisation values
     */
    public static final int defaultPopulation = 100;
    public static final int defaultPopulationInfected = 10;
    public static final int defaultPersonRadius = 5;

    public static final double amountOfConfined = 0.8;
    public static final double amountOfQuarantined = 0.9;
    public static final double amountOfMasked = 0.95;

}
