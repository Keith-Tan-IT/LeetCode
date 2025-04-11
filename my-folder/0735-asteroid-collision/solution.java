class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> output = new Stack<Integer>();
        for (int i = 0; i < asteroids.length; i++) {
            boolean destroyed = false;
            if (output.empty() || output.peek() < 0 || ((output.peek() > 0 && asteroids[i] > 0))) {
                output.push(asteroids[i]);
            }
            else {
                while (!output.empty() && output.peek() > 0 && asteroids[i] < 0) {
                    if(output.peek() == Math.abs(asteroids[i])) {
                        output.pop();
                        destroyed = true;
                        break;
                    }
                    else if (output.peek() > Math.abs(asteroids[i])) {
                        destroyed = true;
                        break;
                    }
                    else if (output.peek() < Math.abs(asteroids[i])) {
                        output.pop();                        
                        System.out.println(destroyed);
                        continue;
                    }
                }
                if (!destroyed) {
                    output.push(asteroids[i]);
                }
            }   
        }
        int[] result = new int[output.size()];
        for (int i = 0; i < output.size(); i++) {
            result[i] = output.get(i);
        }
        return result;  
    }    
}
