package hust.libgdx.tool.views.renderers;

import hust.libgdx.tool.constants.Constant;
import hust.libgdx.tool.utilities.Utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

public class Editor{
	private Stage stage;
	private Rectangle bound;
	
	public Editor(Skin skin){
		stage = new Stage();
		
		bound = new Rectangle();
		bound.x = Utility.getActualValue(Constant.DESIGN_LOCATION.x, true);
		bound.y = Utility.getActualValue(Constant.DESIGN_LOCATION.y, false);
		bound.width = Utility.getActualValue(Constant.DESIGN_SIZE.x, true);
		bound.height = Utility.getActualValue(Constant.DESIGN_SIZE.y, false);
		
		System.out.println("Editor : " + bound);
	}
	
	public Rectangle getBound(){
		return bound;
	}
	
	public Stage getStage(){
		return stage;
	}

	public void render(){
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public boolean contain(Vector2 currentTouchPos) {
		return bound.contains(currentTouchPos);
	}
	
	public boolean contain(Actor actor){
		return stage.getActors().contains(actor, true);
	}

	public void addNewActor(Actor newActor, float x, float y) {
		// set location for new Actor 
		setActorLocation(newActor, x, y);
		
		stage.addActor(newActor);
	}
	
	public void removeActor(Actor actor){
		Array<Actor> actors = stage.getActors();
		
		for (Actor object : actors) {
			if (actor == object){
				// delete actor
				actor.remove();
				return;
			}
		}
	}

	public void setActorLocation(Actor newActor, float x, float y) {
		newActor.setX(x - newActor.getWidth() / 2);
		newActor.setY(y - newActor.getHeight() / 2);
	}
}