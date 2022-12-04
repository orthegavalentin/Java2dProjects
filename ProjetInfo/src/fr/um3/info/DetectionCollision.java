package fr.um3.info;
public class DetectionCollision {
    Panel panel;
    public DetectionCollision(Panel panel){
        this.panel=panel;
    }

    public void checkTile(Personnage personnage){
        int indiceX=(int)personnage.positionCourantX/Panel.TAILLE_BLOC;
        int indiceY=(int)personnage.positionCourantY/Panel.TAILLE_BLOC;

     switch (personnage.direction){
         case UP :personnage.collisionOn=panel.entites[indiceY-1][indiceX].collision;break;
         case DOWN: personnage.collisionOn=panel.entites[indiceY+1][indiceX].collision;break;
         case LEFT: personnage.collisionOn=panel.entites[indiceY][indiceX-1].collision;break;
         case RIGHT: personnage.collisionOn=panel.entites[indiceY][indiceX+1].collision;break;
     }




    }


}
