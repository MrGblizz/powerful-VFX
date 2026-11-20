package io.github.mrgblizz.powerfulvfx;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Point d'entrée commun (client et serveur) de la bibliothèque.
 * Fabric l'appelle au démarrage : il est déclaré dans fabric.mod.json, clé « main ».
 */
public class PowerfulVfx implements ModInitializer {
	public static final String MOD_ID = "powerful_vfx";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Powerful-VFX chargé");
	}
}
