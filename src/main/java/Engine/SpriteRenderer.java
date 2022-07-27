package Engine;

public class SpriteRenderer extends Component {


    @Override
    public void start() {
        System.out.println("Start For SpriteRenderer");
        this.firstTime = false;
    }

    @Override
    public void update(float dt) {
        if(!this.firstTime) {
            System.out.println("Updating For SpriteRenderer");
        }

    }
}
