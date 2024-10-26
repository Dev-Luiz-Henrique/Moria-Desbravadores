export function abbreviateName(name) {
    const ignoreWords = ["dos", "da", "de", "do", "e"];
    const parts = name.trim().split(" ").filter(part => !ignoreWords.includes(part.toLowerCase()));
    
    if (parts.length <= 3) 
        return name; 

    const firstName = parts[0];
    const lastName = parts[parts.length - 1];
    const middleInitials = parts.slice(1, parts.length - 1).map(part => part.charAt(0) + '.').join(' ');
    return `${firstName} ${middleInitials} ${lastName}`;
}