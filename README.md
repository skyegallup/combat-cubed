# Combat Cubed

Combat Cubed is a Minecraft NeoForged mod that adds more combat items and mechanics.

**Work in Progress:** This mod is not complete!

# Feature Progress

- [ ] New items:
    - [ ] Bolas: throwable that applies Snare for 5 seconds
        - [ ] Has a 4 second cooldown
        - [ ] TODO: implement textures and animations
    - [ ] Slings: weapon for throwing certain throwable items further
        - [ ] Pebbles as ammo
        - [ ] Firing potions from slings
        - [ ] Firing fire charges from slings
        - [ ] Animated item, like a bow
        - [ ] Compatible enchants: Unbreaking, Mending, Guidance
    - [ ] Obsidian Arrows: deal ~25% extra damage, but no potion effects
    - [ ] Potion of the Giant: grants Grow I/II and Health Boost I/II for 1/2 minutes
        - Grow effect: +20% larger size per level (upcoming 1.20.5)
        - Brewed using a golden apple
- [ ] New blocks:
    - [ ] Ballista: mounted crossbow turret
        - Fires further and deals more damage than a crossbow
        - Fires bolas REALLY far
        - Can be enchanted with all crossbow enchants
        - Takes damage like an entity to destroy
            - (Probably need to implement this as a rideable entity?)
        - Pilotable by snow golems to automatically attack mobs
- [ ] New effects:
    - [ ] Snare: 10% slow, disables sprinting and flying
- [ ] Allow Thorns enchant on shields
    - [ ] Thorns effect only applies while actively shielding
- [ ] New enchants:
    - [ ] Frost Aspect: applies the powder snow freezing effect + Mining Fatigue I for 4/8 seconds
        - Can be applied to axes
        - Levels I-II (duration scales with level)
    - [ ] Guidance: projectiles only affect the "correct team"
        - e.g. instant damage potions affect enemies; instant healing potions affect teammates
        - Can be applied to slings
        - Only one level, like Infinity
    - [ ] Recovery: decreases shield recovery time by 10/15/20%
        - Can be applied to shields
        - Levels I-III (decrease scales with level)
        - Mutually exclusive with Thorns
    - [ ] Magic Protection: Reduces negative duration effects' durations by (5 x level)%, and negative instant effects by (5 x level)%
        - Can be applied to helmets, chestplates, leggings, and boots
        - Reduction is capped at -50% duration and -65% instant effects
        - Levels I-IV (reduction scales with level)
        - Mutually exclusive with Protection, Blast Protection, Fire Protection, Feather Falling, and Projectile Protection
- [ ] Mod compatibility:
  - [ ] Create for Fabric: Crafting recipes
  - [ ] Team Emblems: Ensure that Guidance works with teams
  - [ ] Masrusj's Progressive Difficulty: Ensure that Recovery works on shields with Soul Jar
