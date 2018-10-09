let dragon_image, loot_image, main_character_image, orc_image, skeleton_image, slime_image, wall_image, defeat_image, won_image;
let dir = 'img/';
let ext = '.png';

function loadImages() {
    dragon_image = loadImage(dir + 'image_dragon' + ext);
    loot_image = loadImage(dir + 'image_loot' + ext);
    main_character_image = loadImage(dir + 'image_main_character' + ext);
    orc_image = loadImage(dir + 'image_orc' + ext);
    skeleton_image = loadImage(dir + 'image_skeleton' + ext);
    slime_image = loadImage(dir + 'image_slime' + ext);
    wall_image = loadImage(dir + 'image_wall' + ext);
    defeat_image = loadImage(dir + 'image_defeat' + ext);
    won_image = loadImage(dir + 'image_won' + ext)
}