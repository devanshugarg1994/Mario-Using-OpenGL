package Engine;

public class FontRenderer extends Component {

    @Override
    public void start() {
        System.out.println("FrontRenderer Started");
        this.firstTime = false;
    }

    @Override
    public void update(float dt) {
        if(!this.firstTime) {
            System.out.println("FrontRenderer Updated");
        }

    }
}
