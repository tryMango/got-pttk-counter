void main() {
    File sectionsToAdd = new File("newSections.txt");
    File storedSections = new File("allSections.txt");

    HashMap<String, Integer> sectionsBufferHashMap = new HashMap<>();

    changeHashMap(storedSections, sectionsBufferHashMap);

    changeHashMap(sectionsToAdd, sectionsBufferHashMap);

    try (FileWriter fileWriter = new FileWriter(storedSections)){
        for (String section : sectionsBufferHashMap.keySet()){
            fileWriter.write(sectionsBufferHashMap.get(section) + " \t" + section + "\n");
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }

    System.out.println("You have collected a total of " + countPoints(sectionsBufferHashMap) + " GOT points.");
}

public Integer countPoints(HashMap<String, Integer> hashMap){
    int pointsSum = 0;
    for (int value : hashMap.values()){
        pointsSum += value;
    }
    return pointsSum;
}

public void changeHashMap(File entryFile, HashMap<String, Integer> hashMap){
    try (Scanner fileReader = new Scanner(entryFile)){
        while(fileReader.hasNextLine()){
            String line = fileReader.nextLine().replace("* ", "");
            if (!line.isEmpty()){
                hashMap.put(line.substring(line.indexOf("\t")+1), Integer.valueOf(line.substring(0, line.indexOf("\t")-1)));
            }
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}