package pemapmodder.spidermine.network.packet;

public class ProtocolInfo{
	// protocol info from PocketMine (https://github.com/PocketMine/PocketeMine-MP).
	// PocketMine is licensed under the GNU Lesser General Public License. Credit of the numbers here are given to @Pocketmine
	public final static byte GENERAL=0x14;
	public final static byte PING=0x00 // receive
			,REQUEST_CONNECT=0x09
			,CLIENT_HANDSHAKE=0x13
			,LOGIN=(byte)0x82
			,LOGIN_STATUS=(byte)0x83
			,CLIENT_SPAWN=(byte)0x84
			,PLAYER_MOT=(byte)0x95
			,CHUNK_REQ=(byte)0x9d
			
			;
	public final static byte PONG=0x01 // send
			,SERVER_HANDSHAKE=0x10
			,KICK=0x15
			,CLIENT_CHAT=(byte)0x85
			,TIME_SET=(byte)0x86
			,ADD_MOB=(byte)0x88
			,ADD_PLAYER=(byte)0x89
			,RM_PLAYER=(byte)0x8a
			,ADD_ENT=(byte)0x8c
			,RM_ENT=(byte)0x8d
			,ADD_ITEM=(byte)0x8e
			,ENT_LOCOMOT=(byte)0x90 // locomotion
			,ENT_POSROT_MOT=(byte)0x93
			,ENT_ROT_HEAD=(byte)0x94
			,RM_BLOCK=(byte)0x97
			,UPDATE_BLOCK=(byte)0x98
			,ADD_PAINT=(byte)0x99
			,EXPLODE=(byte)0x9a
			
			;
	public final static byte START_GAME=(byte)0x87 // unclassified
			,TAKE_ITEM=(byte)0x8f
			,LVL_EVT=(byte)0x9b
			,ENT_EVT=(byte)0x9c
			
			
			;
}
