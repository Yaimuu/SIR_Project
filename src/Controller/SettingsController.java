/**
 * SettingsController : Default settings
 * @project SIR_Project
 * @authors Yamuu - Gagou
 */

package Controller;

public class SettingsController implements Controller
{
    // TODO : Gestion des options
    /**
     * Beta configuration
     */
    public static final double minBetaMap = 0;
    public static final double maxBetaMap = 1;
    public static final double minBetaGraph = 0.0001;
    public static final double maxBetaGraph = 0.01;

    public static final double defaultRecoveryTime = 8;
    public static final int defaultPopulation = 100;
    public static final int defaultPopulationInfected = 10;
}
