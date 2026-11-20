package io.github.mrgblizz.powerfulvfx.client;

import net.fabricmc.api.ClientModInitializer;

/**
 * Point d'entrée client uniquement (jamais chargé sur un serveur dédié).
 * Le rendu des auras et des effets s'y branchera à partir de M1.
 */
public class PowerfulVfxClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// Rien à dessiner pour l'instant.
	}
}
