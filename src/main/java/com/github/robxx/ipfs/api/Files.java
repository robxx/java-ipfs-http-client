package com.github.robxx.ipfs.api;

public class Files {
	String Path = "files/";

	private Files(Chcid builder) {
		//TODO
		this.Path += "chcid"
				+ String.format("?arg=%s&cid-version=%s&hash=%s", builder.arg, builder.cidVersion, builder.hash);
	}
	public static class Chcid {
		// default parameters
		String arg = "/";
		// optional
		int cidVersion;
		String hash;

		public Chcid setArg(String arg) {
			this.arg = arg;
			return this;
		}

		public Chcid setCidVersion(int cidVersion) {
			this.cidVersion = cidVersion;
			return this;
		}

		public Chcid setHash(String hash) {
			this.hash = hash;
			return this;
		}

		public Files build() {
			return new Files(this);
		}
	}

	
	private Files(Ls builder) {
		this.Path += "ls" + String.format("?arg=%s%s%s", builder.arg, 
				builder.L == null ? "": "&L=" + builder.L,
				builder.U == null ? "": "&U=" + builder.U);
	}
	public static class Ls {
		// default parameters
		String arg = "/";
		// optional
		Boolean L, U;

		public Ls setArg(String arg) {
			this.arg = arg;
			return this;
		}

		public Ls setLong(Boolean L) {
			this.L = L;
			return this;
		}

		public Ls setU(Boolean U) {
			this.U = U;
			return this;
		}

		public Files build() {
			return new Files(this);
		}
	}
	
	
	private Files(Cp builder) {
		this.Path += "cp" + String.format("?arg=%s&arg=%s%s", builder.src, builder.dest, 
				builder.parents == null ? "" : "&parents=" + builder.parents);
	}
	public static class Cp {
		String src, dest;
		// default parameters
		
		// optional
		Boolean parents;

		public Cp setParents(Boolean parents) {
			this.parents = parents;
			return this;
		}

		public Files build(String src, String dest) {
			this.src = src;
			this.dest = dest;
			return new Files(this);
		}
	}
	
	
	

	public String call() {
		//TODO
		System.out.println(this.Path);
		return this.Path;
	}

}
