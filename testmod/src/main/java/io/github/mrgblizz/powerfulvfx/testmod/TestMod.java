package io.github.mrgblizz.powerfulvfx.testmod;

import net.fabricmc.api.ModInitializer;
import io.github.mrgblizz.powerfulvfx.testmod.ModItems;

/**
 * Point d'entrée du mod de test.
 * C'est ici que TU enregistreras ton premier item (voir l'étape suivante).
 */
public class TestMod implements ModInitializer {
	public static final String MOD_ID = "powerful_vfx_test";

	@Override
	public void onInitialize() {
		// TODO (toi) : enregistrer le premier item.
		ModItems.initialize();
	}
}
