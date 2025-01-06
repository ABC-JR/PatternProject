import java.util.Random;

interface Levels {
    String nexlevel(double request);
    void setNext(Levels levels);
}


class Level1 implements Levels {
    private Levels nextLevel;

    @Override
    public String nexlevel(double request) {
        if (request < 600000) {
            return "Level 1";
        } else if (nextLevel != null) {
            return nextLevel.nexlevel(request);
        }else {
            return "Request not handled";
        }
    }

    @Override
    public void setNext(Levels levels) {
        this.nextLevel = levels;
    }
}

class Level2 implements Levels {
    private Levels nextLevel;

    @Override
    public String nexlevel(double request) {
        if (request < 1200000) {
            return "Level 2";
        } else if (nextLevel != null) {
            return nextLevel.nexlevel(request);
        }else {
            return "Request not handled";
        }
    }

    @Override
    public void setNext(Levels levels) {
        this.nextLevel = levels;
    }
}

class Level3 implements Levels {
    private Levels nextLevel;

    @Override
    public String nexlevel(double request) {
        if (request < 1800000) {
            return "Level 3";
        } else if (nextLevel != null) {
            return nextLevel.nexlevel(request);
        }else {
            return "Request not handled";
        }
    }

    @Override
    public void setNext(Levels levels) {
        this.nextLevel = levels;
    }
}

class Level4 implements Levels {
    private Levels nextLevel;

    @Override
    public String nexlevel(double request) {
        if (request < 2400000) {
            return "Level 4";
        } else if (nextLevel != null) {
            return nextLevel.nexlevel(request);
        }else {
            return "Request not handled";
        }
    }

    @Override
    public void setNext(Levels levels) {
        this.nextLevel = levels;
    }
}

class Level5 implements Levels {
    private Levels nextLevel;

    @Override
    public String nexlevel(double request) {
        if (request < 3000000) {
            return "Level 5";
        } else if (nextLevel != null) {
            return nextLevel.nexlevel(request);
        } else {
            return "Request not handled";
        }
    }

    @Override
    public void setNext(Levels levels) {
        this.nextLevel = levels;
    }


}