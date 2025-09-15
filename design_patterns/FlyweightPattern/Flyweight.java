package design_patterns.FlyweightPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Flyweight {
    public static class Particle {
        String color, sprite;

        public Particle(String color, String sprite) {
            this.color = color;
            this.sprite = sprite;
        }

    }

    public static class ParticleFactory {
        public HashMap<String, Particle> particles = new HashMap<>();

        public Particle getParticle(String color, String sprite) {
            String key = color + "-" + sprite;
            if (!particles.containsKey(key)) {
                particles.put(key, new Particle(color, sprite));
            }
            return particles.get(key);

        }

    }

    public static class GameObject {
        Particle particle;
        int x, y;
        public GameObject(Particle particle, int x, int y) {
            this.particle = particle;
            this.x = x;
            this.y = y;
        }
        
    }
    public static void main(String[] args) {
        ParticleFactory particleFactory = new ParticleFactory();
        List<GameObject> gameObjects =new ArrayList<>();
        for(int i=1; i<=15; i++)
            gameObjects.add(new GameObject(particleFactory.getParticle("#12346"+i%3, "Some"), i, i+10));

        System.out.println(particleFactory.particles.size());

    }

}
